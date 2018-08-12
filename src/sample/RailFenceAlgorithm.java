package sample;

import sun.rmi.runtime.Log;

public class RailFenceAlgorithm extends FilesHandler {
//    follower methods
     public   char[][] CreateMatrixFromMessageAndLines(int rows, int cols) {

        char[][] result = new char[rows][];

        for (int row = 0; row < result.length; row++) {

            result[row] = new char[cols];
        }

        return result;
    }
     public   String getStringMessageFromMatrix(char[][] matrix)
    {
        String result = "";

        for (int row = 0; row < matrix.length; row++)
        {
            for (int col = 0; col < matrix[row].length; col++)
            {
                if (matrix[row][col] != '\0')
                {
                    result += matrix[row][col];
                }
            }
        }
        return result;
    }
     public   char[][] transpose(char[][] matrix)
    {
        char[][] result =
                CreateMatrixFromMessageAndLines(matrix[0].length, matrix.length);
        for (int row = 0; row < matrix.length; row++)
        {
            for (int col = 0; col < matrix[row].length; col++)
            {
                result[col][row] = matrix[row][col];
            }
        }

        return result;
    }
//    Main Methods
     public   String MessageEncryption(String clearText, int key) {

        String result = "";

        char[][] matrix = CreateMatrixFromMessageAndLines(key, clearText.length());

        int rowIncrement = 1;
        for (int row = 0, col = 0; col < matrix[row].length; col++) {

// if the we reach the last row then we will back to previous row and added into deff col
            if (
                    row + rowIncrement == matrix.length ||
                            row + rowIncrement == -1
                    )
            {
                rowIncrement *= -1;
                System.out.println(matrix.length);
            }

            matrix[row][col] = clearText.charAt(col);
            row += rowIncrement;
        }


            System.out.println(matrix);
        result = getStringMessageFromMatrix(matrix);

         for (int i=0; i<matrix.length; i++){

             for ( int j=0; j<matrix[i].length; j++){

                 System.out.println(matrix[i][j]);
             }

             System.out.println();
         }


         return result;
    }
     public   String MessageDcryption(String cipherText, int key) {

        String result = "";

        char[][] matrix = CreateMatrixFromMessageAndLines(key, cipherText.length());

        int rowIncrement = 1;
        int textIdx = 0;

        for (
                int selectedRow = 0;
                selectedRow < matrix.length;
                selectedRow++
                )
        {
            for (
                    int row = 0, col = 0;
                    col < matrix[row].length;
                    col++
                    )
            {
                if (
                        row + rowIncrement == matrix.length ||
                                row + rowIncrement == -1
                        )
                {
                    rowIncrement *= -1;
                }

                if (row == selectedRow) {

                    matrix[row][col] = cipherText.charAt(textIdx++);
                }

                row += rowIncrement;
            }
        }

        matrix = transpose(matrix);
        result = getStringMessageFromMatrix(matrix);

        for (int row=0; row<matrix.length; row++){

            for ( int col=0; col<matrix[row].length; col++){

                System.out.print(matrix[row][col]+"\n");
            }

            System.out.println();
        }



        return result;
    }
}
