package AgroAid;
import java.util.*;

public class AgroAid {

    static class Farmer {

        String name;
        String phone;

        Farmer(String name, String phone) {
            this.name = name;
            this.phone = phone;
        }
    }

    static class Fertilizer {

        String type;
        String name;
        String reason;
        int priority;

        Fertilizer(String type, String name, String reason, int priority) {
            this.type = type;
            this.name = name;
            this.reason = reason;
            this.priority = priority;
        }
    }

    static class AgroAidSystem {

        HashMap<String, Farmer> farmers = new HashMap<>();

        HashMap<String, ArrayList<Fertilizer>> fertilizerDB = new HashMap<>();

        HashMap<String, String> soilNotes = new HashMap<>();


        AgroAidSystem() {

            soilNotes.put("clay","Clay soil holds water but drains slowly.");
            soilNotes.put("sandy","Sandy soil loses nutrients quickly.");
            soilNotes.put("black","Black soil retains moisture well.");
            soilNotes.put("loamy","Loamy soil is fertile.");
            soilNotes.put("red","Red soil is low in nitrogen.");

            // Rice
            addFertilizer("rice",
                    new Fertilizer("Nitrogen","Urea",
                            "Promotes leaf growth",1));

            addFertilizer("rice",
                    new Fertilizer("Micronutrient","Zinc Sulphate",
                            "Prevents yellow leaves",2));

            // Wheat
            addFertilizer("wheat",
                    new Fertilizer("Nitrogen","Urea",
                            "Improves wheat yield",1));

            // Maize
            addFertilizer("maize",
                    new Fertilizer("Nitrogen","Ammonium Nitrate",
                            "Boosts maize growth",1));

            // Cotton
            addFertilizer("cotton",
                    new Fertilizer("Phosphorus","DAP",
                            "Improves root development",1));

            // Sugarcane
            addFertilizer("sugarcane",
                    new Fertilizer("Nitrogen","Ammonium Sulphate",
                            "Enhances cane growth",1));

            // Tomato
            addFertilizer("tomato",
                    new Fertilizer("Potassium","Muriate of Potash",
                            "Improves fruit quality",1));

            // Potato
            addFertilizer("potato",
                    new Fertilizer("Potassium","Potassium Sulphate",
                            "Supports tuber development",1));
        }


        void addFertilizer(String crop, Fertilizer f){

            fertilizerDB.putIfAbsent(crop,new ArrayList<>());

            fertilizerDB.get(crop).add(f);
        }


        void getRecommendation(String crop,String soil){

            ArrayList<Fertilizer> list = fertilizerDB.get(crop);

            if(list == null){
                System.out.println("Crop not found");
                return;
            }

            PriorityQueue<Fertilizer> pq =
                    new PriorityQueue<>(Comparator.comparingInt(f -> f.priority));

            pq.addAll(list);

            System.out.println("\nRecommended Fertilizers:\n");

            while(!pq.isEmpty()){

                Fertilizer f = pq.poll();

                System.out.println("Type: "+f.type);
                System.out.println("Fertilizer: "+f.name);
                System.out.println("Reason: "+f.reason+"\n");
            }

            if(soilNotes.containsKey(soil))
                System.out.println("Soil Note: "+soilNotes.get(soil));
        }
    }


    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        AgroAidSystem system = new AgroAidSystem();

        System.out.println("===== AgroAid System =====");

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();

        system.farmers.put(phone,new Farmer(name,phone));

        // Show available crops
        System.out.println("\nAvailable Crops: " + system.fertilizerDB.keySet());
        System.out.print("Enter Crop: ");
        String crop = sc.nextLine().toLowerCase();

        // Show available soil types
        System.out.println("\nAvailable Soil Types: " + system.soilNotes.keySet());
        System.out.print("Enter Soil Type: ");
        String soil = sc.nextLine().toLowerCase();

        system.getRecommendation(crop,soil);

        sc.close();
    }
}