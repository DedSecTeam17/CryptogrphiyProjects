package sample;

import java.awt.*;

 class play_fair_algorithm extends FilesHandler{
    // length of Blocks array
    private int length = 0;
    // table for Playfair Encryption
    private String [][] table;
    public play_fair_algorithm(){
        String output = PlayFairEncry("balloon","mohamed");
        String DecryptiondOutput = PlayFairDecry(output,"mohamed");

        // output the results to user
        this.PrintTheTable(table);
        this.printResults(output,DecryptiondOutput);
    }
    public   String PlayFairEncry(String message,String key){
        key = key.toUpperCase();
        key = key.replaceAll("[^A-Z]", "");
        key = key.replace("J", "I");
        table = this.EncryptionTable(key);
        String output = Encryption(message.toUpperCase());
        return  output;
    }
    public  String PlayFairDecry(String message,String key){
        key = key.toUpperCase();
        key = key.replaceAll("[^A-Z]", "");
        key = key.replace("J", "I");
        table = this.EncryptionTable(key);
        String DecryptiondOutput = Decryption(message.toUpperCase());
        return  DecryptiondOutput;
    }







    // parses any input string to remove numbers, punctuation,
    // replaces any J's with I's, and makes string all caps
    private String parseString(String parse){
        parse = parse.toUpperCase();
        parse = parse.replaceAll("[^A-Z]", "");
        parse = parse.replace("J", "I");
        return parse;
    }
    // creates the Encryption table based on some input string (already parsed)
//    start from first character and compare it with whole matrix anc chick if there are a duplication
//
    private String[][] EncryptionTable(String key){
        String[][] playfairTable = new String[5][6];
        String keyString = key+"ABCDEFGHJIKLMNOPQRSTUVWXYZ";
        // fill string array with empty string
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                playfairTable[i][j] = "";
        for(int k = 0; k < keyString.length(); k++){
            boolean repeat = false;
            boolean used = false;
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    if(playfairTable[i][j].equals("" + keyString.charAt(k))){
                        repeat = true;
                    }else if(playfairTable[i][j].equals("") && !repeat && !used){
                        playfairTable[i][j] = "" + keyString.charAt(k);
                        used = true;
                    }
                }
            }
        }
        return playfairTable;
    }
    // Encryption: takes input (all upper-case), encodes it, and returns output
    private String Encryption(String in){

//        group character into 2 half if character is duplicated add.png x to first duplicated and move on


        length = (int) in.length() / 2 + in.length() % 2;
        // insert x between double-letter Blockss & redefines "length"



        for(int i = 0; i < (length - 1); i++){
            if(in.charAt(2 * i) == in.charAt(2 * i + 1)){
                in = new StringBuffer(in).insert(2 * i + 1, 'X').toString();
//                update length
                length = (int) in.length() / 2 + in.length() % 2;
            }
        }

        // adds an x to the last Blocks, if necessary
        String[] Blocks = new String[length];
        for(int j = 0; j < length ; j++){
//            if the half result not equals then add.png x at the end like 4 and 3 character then add.png x at last

//            if counter at last of character mohamed size 3==index(3) and 6/2 ==3  then  add.png x
            if(j == (length - 1) && in.length() / 2 == (length - 1))
                in = in + "X";


//            create String block and put them inside array of String String
//            example mohamed become mohamedx   , m+o  ,  h+a ,  m+e ,  d+x
            Blocks[j] = in.charAt(2 * j) +""+ in.charAt(2 * j + 1);
        }
        // encodes the Blockss and returns the output
        String EncryptionResult = "";
        String[] EncryptedBlocks = new String[length];
//        encryption for blocks blocks by blocks then added to main String
        EncryptedBlocks = BlockEncryption(Blocks);
        for(int k = 0; k < length; k++)
            EncryptionResult = EncryptionResult + EncryptedBlocks[k];
        return EncryptionResult;
    }
    // encodes the Blocks block input with the Encryption's specifications
    private String[] BlockEncryption(String di[]){
        String[] enc = new String[length];
        for(int i = 0; i < length; i++){
            // get the first block of String
            char a = di[i].charAt(0);
            // get the second block of String
            char b = di[i].charAt(1);
//            using the table get the points of character and do changes over them
//            example let`s consider      m(r1,c1)        o(r2,c2)   from the table become like this m(0,0) m(0,1)
//            from this example r1==r2 that mean they are in the same row then shift right by add.png 1 into point for column then m(0,1) and o(0,2)
//            and if them in diff row`s then we get the cut point for them by change col point between them example
//            n(0,2)   i(2,0)    become new n(2,2)  new i(0,0)
            int r1 = (int) getArrayCellPoints(a).getX();
            int r2 = (int) getArrayCellPoints(b).getX();
            int c1 = (int) getArrayCellPoints(a).getY();
            int c2 = (int) getArrayCellPoints(b).getY();
//            System.out.println(r1);
//            System.out.println(r2);
//            System.out.println(c1);
//            System.out.println(c2);

            // case 1: letters in Blocks are of same row, shift columns to right
            if(r1 == r2){
                c1 = (c1 + 1) % 5;
                c2 = (c2 + 1) % 5;

                // case 2: letters in Blocks are of same column, shift rows down
            }else if(c1 == c2){
                r1 = (r1 + 1) % 5;
                r2 = (r2 + 1) % 5;

                // case 3: letters in Blocks form rectangle, swap first column # with second column #
            }else{
                int temp = c1;
                c1 = c2;
                c2 = temp;
            }

            //performs the table look-up and puts those values into the encoded array
            enc[i] = table[r1][c1] + "" + table[r2][c2];
        }
        return enc;
    }
    // Decryptions the output given from the Encryption and Decryption methods (opp. of encoding process)
    private String Decryption(String out){
        String Decryptiond = "";
        for(int i = 0; i < out.length() / 2; i++){


//            first block
            char a = out.charAt(2*i);
//            second block
            char b = out.charAt(2*i+1);


//            points
            int r1 = (int) getArrayCellPoints(a).getX();
            int r2 = (int) getArrayCellPoints(b).getX();
            int c1 = (int) getArrayCellPoints(a).getY();
            int c2 = (int) getArrayCellPoints(b).getY();



// 4 indicate the number of cell will shift without current character
            if(r1 == r2){
                c1 = (c1 + 4) % 5;
                c2 = (c2 + 4) % 5;
            }else if(c1 == c2){
                r1 = (r1 + 4) % 5;
                r2 = (r2 + 4) % 5;
            }else{
                int temp = c1;
                c1 = c2;
                c2 = temp;
            }
            Decryptiond = Decryptiond + table[r1][c1] + table[r2][c2];
        }
        return Decryptiond;
    }
    // returns a point containing the row and column of the letter
    private Point getArrayCellPoints(char c){
        Point pt = new Point(0,0);
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if(c == table[i][j].charAt(0))
                    pt = new Point(i,j);
        return pt;
    }
    // prints the Encryption table out for the user
    private void PrintTheTable(String[][] printedTable){
        System.out.println("This is the Encryption table from the given keyword.");
        System.out.println();

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                System.out.print(printedTable[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    // prints results (encoded and Decryptiond)
    private void printResults(String enc, String dec){
        System.out.println("This is the encoded message:");
        System.out.println(enc);
        System.out.println();
        System.out.println("This is the Decryptiond message:");
        System.out.println(dec);
    }
}
