public class HashTable {
    int[] H;
    int hsize;
    String hfunction;
    String openAddrType;

    HashTable(int hsize, String hfunction, String openAddrType) {
        this.hsize = hsize;
        H = new int[hsize];
        this.hfunction = hfunction;
        this.openAddrType = openAddrType;

        // Initialize hash table with -1 to indicate empty slots
        for (int i = 0; i < hsize; i++) {
            H[i] = 0;
        }
    }

    // Complete the fillHashTable method
    void fillHashTable(int key) {
        int orig_addr = 0;
        // Step1: Find an original address according to the Hash function
        if (hfunction.equals("Division")) {
            orig_addr = key % hsize;
        } else if (hfunction.equals("Folding")) {
            int C = 100;
            orig_addr = (key / C + key % C) % hsize;
        }

        // Step2: Check collision. If collision, find an open address
        int i = 0;
        int fi = 0; // fi = f(i)
        int addr = orig_addr;

        while (H[addr] != 0) {
            if (openAddrType.equals("Linear")) {
                fi = i;
            } else if (openAddrType.equals("Quadratic")) {
                fi = i * i;
            }
            addr = (orig_addr + fi) % hsize;
            i++;
        }

        H[addr] = key;
    }

    // Complete the search method
    void search(int key) {
        int orig_addr = 0;

        // Step1: Find an original address according to the Hash function
        if (hfunction.equals("Division")) {
            orig_addr = key % hsize;
        } else if (hfunction.equals("Folding")) {
            int C = 100;
            orig_addr = (key / C + key % C) % hsize;
        }

        // Step2: Check collision. If collision, find the key in an open address
        int i = 0;
        int fi = 0; // fi = f(i)
        int addr = orig_addr;

        while (H[addr] != -1 && H[addr] != key) {
            if (openAddrType.equals("Linear")) {
                fi = i;
            } else if (openAddrType.equals("Quadratic")) {
                fi = i * i;
            }
            addr = (orig_addr + fi) % hsize;
            i++;
        }

        if (H[addr] == key) {
            System.out.println(key + " is found at " + addr);
        } else {
            System.out.println(key + " is not found in the hash table");
        }
    }

    // Method to delete a key from the hash table
    void delete(int key) {
        int orig_addr = 0;

        // Step1: Find the original address according to the Hash function
        if (hfunction.equals("Division")) {
            orig_addr = key % hsize;
        } else if (hfunction.equals("Folding")) {
            int C = 100;
            orig_addr = (key / C + key % C) % hsize;
        }

        // Step2: Find the key and mark it as deleted
        int i = 0;
        int fi = 0; // fi = f(i)
        int addr = orig_addr;

        while (H[addr] != -1 && H[addr] != key) {
            if (openAddrType.equals("Linear")) {
                fi = i;
            } else if (openAddrType.equals("Quadratic")) {
                fi = i * i;
            }
            addr = (orig_addr + fi) % hsize;
            i++;
        }

        if (H[addr] == key) {
            H[addr] = 0; // Mark the key as deleted
            System.out.println(key + " is deleted from " + addr);
        } else {
            System.out.println(key + " is not found in the hash table");
        }
    }

    // Method to print the hash table
    void print() {
        for (int i = 0; i < hsize; i++) {
            System.out.print(H[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] A = {12, 78, 45, 65, 59, 74, 26, 39};
        System.out.print("A [] : ");
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();
        System.out.println(
                "Hashing data in A to Hashtable1 of size 13 \n using the division hash function and solve collision using linear probing");

        // Create hashTable1
        HashTable HashTable1 = new HashTable(13, "Division", "Linear");
        for (int i = 0; i < A.length; i++) {
            HashTable1.fillHashTable(A[i]);
        }
        // print the HashTable1

        System.out.print("Your HashTable1 [] :   ");
        HashTable1.print();
        System.out.println("\nThe correct answer is: 78 65 26 39 0 0 45 59 0 74 0 0 12");
        System.out.println("\n----------------------------------------");

        System.out.println(
                "Hashing data in A to Hashtable2 of size 13 \n using the division hash function and open addressing collision resolution with quadratic probing");
        // Create HashTable2
        HashTable HashTable2 = new HashTable(13, "Division", "Quadratic");
        for (int i = 0; i < A.length; i++) {
            HashTable2.fillHashTable(A[i]);
        }

        System.out.print("Your HashTable2 [] :    ");
        HashTable2.print();
        System.out.println("\nThe correct answer is : 78 65 0 39 26 0 45 59 0 74 0 0 12");
        System.out.println("\n----------------------------------------");

        int[] B = {1278, 2566, 2093, 401, 6655, 1020, 87, 53};
        System.out.print("B [] : ");
        for (int i = 0; i < B.length; i++) {
            System.out.print(B[i] + " ");
        }
        System.out.println(
                "\nHashing data in B to Hashtable3 of size 17 \n using the folding hash function and open addressing collision resolution with linear probing");

        // Create HashTable3
        HashTable HashTable3 = new HashTable(17, "Folding", "Linear");
        for (int i = 0; i < B.length; i++) {
            HashTable3.fillHashTable(B[i]);
        }

        System.out.print("Your HashTable3 [] :                  ");
        HashTable3.print();
        System.out.println(
                "\nThe correct answer is HashTable3 [] : 0 0 6655 87 53 1278 2566 401 0 0 0 2093 0 1020 0 0 0");
        System.out.println("\n----------------------------------------");

        System.out.println(
                "Hashing data in B to Hashtable4 of size 17 \n using Folding hash function and Open addressing collision resolution with Quadratic probing");

        // Create HashTable4
        HashTable HashTable4 = new HashTable(17, "Folding", "Quadratic");
        for (int i = 0; i < B.length; i++) {
            HashTable4.fillHashTable(B[i]);
        }

        System.out.print("Your HashTable4 [] :                  ");
        HashTable4.print();
        System.out.println(
                "\nThe correct answer is HashTable4 [] : 0 53 6655 87 0 1278 2566 0 0 401 0 2093 0 1020 0 0 0");
        System.out.println();
        System.out.println("\n----------------------------------------");

        // ---------------------------------------------------------------
        System.out.println("Test searching data in the hash table");
        System.out.println("Search for 26 in HashTable1");
        HashTable1.search(26);
        System.out.println("The correct answer is  26 is found at address 2");
        System.out.println("\n----------------------------------------");

        System.out.println("Search for 26 in HashTable2");
        HashTable2.search(26);
        System.out.println("The correct answer is  26 is found at address 4");

        System.out.println("\n----------------------------------------");

        System.out.println("Search for 53 in HashTable3");
        HashTable3.search(53);
        System.out.println("The correct answer is  53 is found at address 4");
        System.out.println("\n----------------------------------------");

        System.out.println("Search for 53 in HashTable4");
        HashTable4.search(53);
        System.out.println("The correct answer is  53 is found at address 1");

        // Test deleting data in the hash table
        System.out.println("Delete 26 from HashTable1");
        HashTable1.delete(26);
        HashTable1.print();
        System.out.println("\nThe correct answer is: 78 65 0 39 0 0 45 59 0 74 0 0 12");

        System.out.println("\n-------------------------------------------------------");

        System.out.println("Delete 26 from HashTable2");
        HashTable2.delete(26);
        HashTable2.print();
        System.out.println("\nThe correct answer is: 78 65 0 39 0 0 45 59 0 74 0 0 12");

        System.out.println("\n-------------------------------------------------------");

        System.out.println("Delete 53 from HashTable3");
        HashTable3.delete(53);
        HashTable3.print();
        System.out.println("\nThe correct answer is: 0 0 6655 87 0 1278 2566 401 0 0 0 2093 0 1020 0 0 0");

        System.out.println("\n-------------------------------------------------------");

        System.out.println("Delete 53 from HashTable4");
        HashTable4.delete(53);
        HashTable4.print();
        System.out.println("\nThe correct answer is: 0 0 6655 87 0 1278 2566 0 0 401 0 2093 0 1020 0 0 0");
    }
}
