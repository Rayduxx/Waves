package tn.esprit.test;

import tn.esprit.models.Item;
import tn.esprit.services.ServiceItem;

public class Main {
    public static void main(String[] args) {

        Item AddItem = new Item(1,"Invisible", "beautiful track","NTO",29);
        Item EditItem = new Item(1,"Alter Ego", "melodic audio clip","NTO",55);
        Item DeleteItem = new Item(1,"Alter Ego", "melodic audio clip","NTO",55);
        ServiceItem ItemS = new ServiceItem();

        ItemS.Add(AddItem);
        ItemS.Update(EditItem);
        ItemS.Delete(DeleteItem);

        System.out.println(ItemS.getAll());
    }
}