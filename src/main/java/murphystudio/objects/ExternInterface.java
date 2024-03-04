package murphystudio.objects;

import javax.sound.midi.*;
import javax.sound.sampled.*;
import java.io.*;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;

public class ExternInterface {

    public MidiDevice MidiInput = null;
    public MidiDevice MidiOutput = null;

    // Volume interface
    private Mixer mixer = null;
    private Mixer microphone = null;

    public FloatControl volume = null;
    private Line line = null;

    private final ArrayList<Mixer> outputMixers;
    private final ArrayList<Mixer> inputMixers;
    private final ArrayList<MidiDevice> inputMidiDevice;
    private final ArrayList<MidiDevice> outputMidiDevice;

    public Sequencer sequencer;
    private Synthesizer synthesizer;
    private Track currentTrack;

    public ExternInterface() {
        this.outputMixers = new ArrayList<>();
        this.inputMixers = new ArrayList<>();
        this.inputMidiDevice = new ArrayList<>();
        this.outputMidiDevice = new ArrayList<>();

        initMidiDevice();
        initMixers();

        try {
            this.sequencer = MidiSystem.getSequencer();
            this.synthesizer = MidiSystem.getSynthesizer();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void initMidiDevice()
    {
        // On récupère les midi devices
        MidiDevice midiDevice;
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();

        for (int i = 0; i < infos.length; i++) {
            try {
                midiDevice = MidiSystem.getMidiDevice(infos[i]);

                if ( midiDevice.getMaxTransmitters() != 0 && ! midiDevice.isOpen())
                {
                    this.inputMidiDevice.add(midiDevice);
                    //if ( this.MidiInput == null ) setMidiDevice(midiDevice);
                }
                if ( midiDevice.getMaxReceivers() != 0 && ! midiDevice.isOpen())
                {
                    this.outputMidiDevice.add(midiDevice);
                    //if ( this.MidiOutput == null ) setMidiDevice(midiDevice);
                }

                // If no device set

            } catch (MidiUnavailableException ignored) {}
        }
    }

    private void initMixers()
    {
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();


        for (Mixer.Info mixerInfo : mixers) {
            // Getting the speaker
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            /*
            System.out.println("SPEAKER : " + mixer.isLineSupported(Port.Info.SPEAKER));
            System.out.println("MICROPHONE : " + mixer.isLineSupported(Port.Info.MICROPHONE));
            System.out.println("HEADPHONE : " + mixer.isLineSupported(Port.Info.HEADPHONE));
            System.out.println("LINE_OUT : " + mixer.isLineSupported(Port.Info.LINE_OUT));
            System.out.println("LINE_IN : " + mixer.isLineSupported(Port.Info.LINE_IN));
            System.out.println("COMPACT_DISC : " + mixer.isLineSupported(Port.Info.COMPACT_DISC));
*/
            // HEADPHONE SPEAKER
            if (mixer.isLineSupported(Port.Info.SPEAKER)) {
                this.outputMixers.add(mixer);

                // If no mixer output set
                if (this.mixer == null) this.setMixer(mixer);
            }
            // MICROPHONE MIXER TODO
            else if (mixer.isLineSupported(Port.Info.MICROPHONE)) {
                this.inputMixers.add(mixer);

                if (this.microphone == null) this.setMicrophone(mixer);
            }
        }
    }

    public void setMixer(Mixer mixer) {
        if ( this.line != null && this.line.isOpen())
            this.line.close();

        this.mixer = mixer;
        // Getting line infos list
        Line.Info [] lineInfos = mixer.getTargetLineInfo(); // target, not source

        for (Line.Info lineInfo : lineInfos) {
            boolean isOpen = true;
            Line line = null;

            try {
                line = mixer.getLine(lineInfo);
                isOpen = line.isOpen() || line instanceof Clip;

                if ( ! isOpen )
                    line.open();

                this.line = line;

                if ( ! System.getProperty("os.name").equals("Linux") )
                {
                    FloatControl volCtrl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                    this.volume = volCtrl;
                }

            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }

    public void initKeyboard()
    {
        if ( this.MidiInput == null || this.MidiOutput == null ) return;

        // Get the transmitter class from your input device
        Transmitter transmitter = null;
        try {
            // Open a connection to your input device
            if ( ! this.MidiInput.isOpen() )
                this.MidiInput.open();

            if ( ! this.MidiOutput.isOpen() )
                this.MidiOutput.open();

            if ( ! this.synthesizer.isOpen() )
                this.synthesizer.open();

            // Open a connection to the default sequencer (as specified by MidiSystem)
            if ( ! this.sequencer.isOpen() )
                sequencer.open();

            transmitter = this.MidiInput.getTransmitter();
            // Get the receiver class from your sequencer
            Receiver receiver = sequencer.getReceiver();

            MidiChannel channel = synthesizer.getChannels()[0];
            CustomReceiver customReceiver = new CustomReceiver(channel, receiver);

            // Bind the transmitter to the receiver so the receiver gets input from the transmitter
            transmitter.setReceiver(customReceiver);
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void setMicrophone(Mixer mixer)
    {
        this.microphone = mixer;
    }

    public void setMidiDevice(MidiDevice device)
    {
        if ( this.MidiInput != null ) MidiInput.close();
        this.MidiInput = device;

        initKeyboard();
    }

    public void setMidiOutput(MidiDevice device)
    {
        if ( this.MidiOutput != null ) MidiOutput.close();

        this.MidiOutput = device;
    }

    /*
     * RECORD METHOD
     */

    public void startRecording()
    {
        if ( this.MidiInput == null || this.MidiOutput == null ) return;

        try {
            // Create a new sequence
            Sequence sequence = new Sequence(Sequence.PPQ, 24);
            // And of course a track to record the input on
            this.currentTrack = sequence.createTrack();

            // Do some sequencer settings
            sequencer.setSequence(sequence);
            sequencer.setTickPosition(0);
            sequencer.recordEnable(currentTrack, -1);
            // And start recording
            sequencer.startRecording();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    public Sequence stopRecording()
    {
        sequencer.recordDisable(currentTrack);
        sequencer.stopRecording();

        this.sequencer.setTickPosition(0);

        Sequence sequence = this.sequencer.getSequence();

        try {
            this.sequencer.setSequence(sequence);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        return sequencer.getSequence();
    }


    public ArrayList<MidiDevice> getInputMidiDevice() {
        return inputMidiDevice;
    }

    public ArrayList<MidiDevice> getOutputMidiDevice() {
        return outputMidiDevice;
    }

    public ArrayList<Mixer> getInputMixers() {
        return inputMixers;
    }

    public ArrayList<Mixer> getOutputMixers() {
        return outputMixers;
    }

    public void close()
    {
        if ( this.MidiInput != null && this.MidiInput.isOpen() )
            this.MidiInput.close();

        if ( this.MidiOutput != null && this.MidiOutput.isOpen() )
            this.MidiOutput.close();

        if ( this.synthesizer != null && this.synthesizer.isOpen() )
            this.synthesizer.close();

        if ( this.sequencer != null && this.sequencer.isOpen() )
            this.sequencer.close();

        if ( this.line != null && this.line.isOpen() )
            this.line.close();

        if ( this.mixer != null && this.mixer.isOpen() )
            this.mixer.close();

        if ( this.microphone != null && this.microphone.isOpen() )
            this.microphone.close();
    }


    public ArrayList<Integer> getAlsaSinkNumberFromPid()
    {
        String PID = getProcessId("<PID>");

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream alsaStream = loader.getResourceAsStream("bash/alsaGetSink.sh");
        String alsaContent = convertStreamToString(alsaStream);
        File alsaFileTmp = new File(".alsaGetSink");
        if(!alsaFileTmp.exists()){
            try {
                alsaFileTmp.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        alsaFileTmp.setWritable(true);
        alsaFileTmp.setExecutable(true);
        alsaFileTmp.deleteOnExit();

        FileOutputStream fop = null;
        try {
            fop = new FileOutputStream(alsaFileTmp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fop.write(alsaContent.getBytes());
            fop.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] command = { alsaFileTmp.getAbsolutePath() };
        Process process = null;



        ArrayList<Integer> sinks = new ArrayList<Integer>();

        try {
            process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));
            String s;
            while ((s = reader.readLine()) != null){
                if ( s.contains(PID) ) sinks.add(Integer.parseInt(s.split(":")[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // +1 because there is a sink which is created after the call of this function
        if (sinks.size() != 0){
            sinks.add(sinks.get( sinks.size() - 1 ) + 1);
        }
        return sinks;
    }

    private static String getProcessId(final String fallback) {
        // Note: may fail in some JVM implementations
        // therefore fallback has to be provided

        // something like '<pid>@<hostname>', at least in SUN / Oracle JVMs
        final String jvmName = ManagementFactory.getRuntimeMXBean().getName();
        final int index = jvmName.indexOf('@');

        if (index < 1) {
            // part before '@' empty (index = 0) / '@' not found (index = -1)
            return fallback;
        }

        try {
            return Long.toString(Long.parseLong(jvmName.substring(0, index)));
        } catch (NumberFormatException e) {
            // ignore
        }
        return fallback;
    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
