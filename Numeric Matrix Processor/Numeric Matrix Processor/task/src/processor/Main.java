package processor;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    //Stage 5 & 6
    Scanner sc = new Scanner(System.in);
    int n;
    int m;
    double [][] mA;
    double [][] mB;

    while(true) {
      System.out.println("1. Add matrices\r\n" +
          "2. Multiply a matrix to a constant\r\n" +
          "3. Multiply matrices\r\n" +
          "4. Transpose a matrix\r\n" +
          "5. Calculate a determinant\r\n" +
          "6. Inverse matrix\r\n" +
          "0. Exit\r\n Your choice: ");
      switch (sc.nextInt()) {
        case 0:
          return;
        case 1:
          System.out.println("Enter the size of the first matrix: ");
          n = sc.nextInt();
          m = sc.nextInt();
          mA = new double [n][m];
          System.out.println("Enter the first matrix:");
          for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
              mA[i][j] = sc.nextDouble();
            }
          }
          System.out.println("Enter the size of the second matrix: ");
          if(n != sc.nextInt() ||	m != sc.nextInt()) {
            System.out.println("ERROR");
            System.out.println();
            sc.nextInt();
            break;
          }
          System.out.println("Enter the second matrix:");
          mB = new double [n][m];
          for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
              mB[i][j] = sc.nextDouble();
            }
          }
          System.out.println("The addition result is: ");
          add(mA, mB);
          System.out.println();
          break;
        case 2:
          System.out.println("Enter the size of a matrix: ");
          n = sc.nextInt();
          m = sc.nextInt();
          mA = new double [n][m];
          System.out.println("Enter a matrix:");
          for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
              mA[i][j] = sc.nextDouble();
            }
          }
          System.out.println("Enter a constant:");
          double c = sc.nextInt();
          System.out.println("The multiplication result is:");
          printMatrix(multiplyMatrixConstant(mA,c));
          System.out.println();
          break;
        case 3:
          System.out.println("Enter the size of the first matrix: ");
          n = sc.nextInt();
          m = sc.nextInt();
          mA = new double [n][m];
          System.out.println("Enter the first matrix:");
          for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
              mA[i][j] = sc.nextDouble();
            }
          }
          System.out.println("Enter the size of the second matrix: ");
          if(m != sc.nextInt()) {
            System.out.println("ERROR");
            System.out.println();
            sc.nextInt();
            break;
          }
          n = sc.nextInt();
          System.out.println("Enter the second matrix:");
          mB = new double [n][m];
          for(int i = 0; i<m; i++) {
            for(int j = 0; j<n; j++) {
              mB[j][i] = sc.nextDouble();
            }
          }
          System.out.println("The multiplication result is: ");
          mulWithConstant(mA, mB);
          System.out.println();
          break;
        case 4:
          System.out.println("1. Main diagonal\r\n" +
              "2. Side diagonal\r\n" +
              "3. Vertical line\r\n" +
              "4. Horizontal line\r\n Your choice: ");
          int choice = sc.nextInt();
          if(choice<1 || choice>4) {
            System.out.println();
            break;
          }
          System.out.println("Enter the size of the matrix: ");
          n = sc.nextInt();
          m = sc.nextInt();
          if(choice<3) {
            if(n!=m) {
              System.out.println("ERROR: The matrix mast be square!");
              System.out.println();
              break;
            }
          }
          mA = new double [n][m];
          System.out.println("Enter the matrix:");
          for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
              mA[i][j] = sc.nextDouble();
            }
          };
          System.out.println("The matrix transposition is: ");
          switch (choice) {
            case 1:
              printMatrix(matrixTranspositionMain(mA));
              System.out.println();
              break;
            case 2:
              mTS(mA);
              break;
            case 3:
              mTV(mA);
              break;
            case 4:
              mTH(mA);
              break;
            default:
              break;
          }
          break;
        case 5:

          System.out.println("Enter the size of the matrix: ");
          n = sc.nextInt();
          if(n!=sc.nextInt()) {
            System.out.println("ERROR: The matrix mast be square!");
            System.out.println();
            break;
          }
          mA = new double [n][n];
          System.out.println("Enter the matrix:");
          for(int i = 0; i<n; i++) {
            for(int j = 0; j<n; j++) {
              mA[i][j] = sc.nextDouble();
            }
          };
          System.out.println("The determinant of the matrix is: ");
          System.out.println(mD(mA));
          System.out.println();
          break;

        case 6:

          System.out.println("Enter the size of the matrix: ");
          n = sc.nextInt();
          if(n!=sc.nextInt()) {
            System.out.println("ERROR: The matrix mast be square!");
            System.out.println();
            break;
          }
          mA = new double [n][n];
          System.out.println("Enter the matrix:");
          for(int i = 0; i<n; i++) {
            for(int j = 0; j<n; j++) {
              mA[i][j] = sc.nextDouble();
            }
          };
          double det = mD(mA);
          if(det==0) {
            System.out.println("ERROR: The matrix is singular!");
            System.out.println();
            break;
          }
          System.out.println("The inverse matrix is: ");
          mI(mA, det);
          System.out.println();
          break;

        default:
          break;
      }

    }
  }

  private static void mI(double[][] matrixA, double det) {
    double [][] matrix = matrixTranspositionMain(matrixA);
    double [][] matrixC = cofMat(matrix);
    det = 1/det;
    printMatrix(multiplyMatrixConstant(matrixC, det));
  }

  private static double[][] cofMat(double[][] matrixA) {
    int n = matrixA.length;
    double[][] matrixC = new double [n][n];
    double[][] matrixB = new double [n-1][n-1];
    for(int i = 0; i<n; i++) {
      for(int j = 0; j<n; j++) {
        matrixB = mMatrixByRow(matrixA, i,j);
        matrixC[i][j] = ((i+j)%2==0?1:-1)* mD(matrixB);
      }
    }
    return matrixC;
  }


  private static double mD(double[][] matrixA) {
    int n = matrixA.length;
    if (n == 1 ) {return matrixA[0][0];}
    if (n == 2 ) {
      return matrixA[0][0]*matrixA[1][1]-matrixA[0][1]*matrixA[1][0];
    }
    double det = 0;
    double[][] matrixB = new double [n-1][n-1];
    for(int j = 0; j<n; j++) {
      matrixB = MinorMatrixBy1row(matrixA, j);
      det+= (j%2==0?1:-1)*matrixA[0][j]* mD(matrixB);
    }

    return det;
  }

  private static double[][] MinorMatrixBy1row(double[][] matrixA, int jIndex) {
    int n = matrixA.length;
    double[][] matrixB = new double [n-1][n-1];
    for(int i = 1; i<n; i++) {
      for(int j = 0, k = 0; j<n; j++, k++) {
        if(jIndex == j) {
          k--;
          continue;
        }
        matrixB[i-1][k] = matrixA[i][j];
      }
    };
    return matrixB;
  }

  private static double[][] mMatrixByRow(double[][] matrixA, int iIndex, int jIndex) {
    int n = matrixA.length;
    double[][] matrixB = new double [n-1][n-1];
    for(int i = 0, l = 0; i<n; i++, l++) {
      if(iIndex == i) {
        l--;
        continue;
      }
      for(int j = 0, k = 0; j<n; j++, k++) {
        if(jIndex == j) {
          k--;
          continue;
        }
        matrixB[l][k] = matrixA[i][j];
      }
    };
    return matrixB;
  }


  private static void mTV(double[][] matrix) {
    for(int i = 0; i<matrix.length; i++) {
      for(int j = 0; j<matrix[0].length; j++) {
        System.out.print(matrix[i][matrix[0].length-1-j]);
        System.out.print(" ");
      }
      System.out.println();
    }
  }

  private static void mTH(double[][] matrix) {
    for(int i = 0; i<matrix.length; i++) {
      for(int j = 0; j<matrix[0].length; j++) {
        System.out.print(matrix[matrix.length-1-i][j]);
        System.out.print(" ");
      }
      System.out.println();
    }
  }

  static void mTS(double[][] matrix) {
    for(int i = 0; i<matrix.length; i++) {
      for(int j = 0; j<matrix.length; j++) {
        System.out.print(matrix[matrix.length-1-j][matrix.length-1-i]);
        System.out.print(" ");
      }
      System.out.println();
    }

  }

  static void add(double[][] matrixA, double[][] matrixB) {
    for(int i = 0; i<matrixA.length; i++) {
      for(int j = 0; j<matrixA[0].length; j++) {
        System.out.print(matrixA[i][j]+matrixB[i][j]);
        System.out.print(" ");
      }
      System.out.println();
    }
  }

  static double[][] multiplyMatrixConstant(double[][] matrix, double c) {
    int n = matrix.length;
    int m = matrix[0].length;
    double[][] matrixM = new double[n][m];
    for(int i = 0; i<n; i++) {
      for(int j = 0; j<m; j++) {
        matrixM[i][j] = matrix[i][j]*c;
      }
    }
    return matrixM;
  }

  static void mulWithConstant(double[][] matrixA, double[][] matrixB) {
    double resMatrix = 0;
    for(int i = 0; i<matrixA.length; i++) {
      for(int j = 0; j<matrixB.length; j++) {
        for (int k = 0; k < matrixA[0].length; k++) {
          resMatrix += matrixA[i][k]*matrixB[j][k];
        }
        System.out.print(resMatrix);
        resMatrix = 0;
        System.out.print(" ");
      }
      System.out.println();
    }
  }

  static double[][] matrixTranspositionMain(double[][] matrix) {
    int n = matrix.length;
    double[][] matrixT = new double [n][n];
    for(int i = 0; i<n; i++) {
      for(int j = 0; j<n; j++) {
        matrixT[i][j] = matrix[j][i];
      }
    }
    return matrixT;
  }

  static void printMatrix (double [][] matrix) {
    int n = matrix.length;
    for(int i = 0; i<n; i++) {
      for(int j = 0; j<n; j++) {
        if(j == 0) System.out.printf("% #.6f", matrix[i][j]);
        else System.out.printf("% #8.6f", matrix[i][j]);
        System.out.print(" ");
      }
      System.out.println();
    }


    /*//Stage 4
    Scanner sc = new Scanner(System.in);
    int n;
    int m;
    double [][] mA;
    double [][] mB;

    while(true) {
      System.out.println("1. Add matrices\r\n" +
          "2. Multiply a matrix to a constant\r\n" +
          "3. Multiply matrices\r\n" +
          "4. Transpose a matrix\r\n" +
          "0. Exit\r\n Your choice: ");
      switch (sc.nextInt()) {
        case 0:
          return;
        case 1:
          System.out.println("Enter the size of the first matrix: ");
          n = sc.nextInt();
          m = sc.nextInt();
          mA = new double [n][m];
          System.out.println("Enter the first matrix:");
          for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
              mA[i][j] = sc.nextDouble();
            }
          }
          System.out.println("Enter the size of the second matrix: ");
          if(n != sc.nextInt() ||	m != sc.nextInt()) {
            System.out.println("ERROR");
            System.out.println();
            sc.nextInt();
            break;
          }
          System.out.println("Enter the second matrix:");
          mB = new double [n][m];
          for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
              mB[i][j] = sc.nextDouble();
            }
          }
          System.out.println("The addition result is: ");
          add(mA, mB);
          System.out.println();
          break;
        case 2:
          System.out.println("Enter the size of a matrix: ");
          n = sc.nextInt();
          m = sc.nextInt();
          mA = new double [n][m];
          System.out.println("Enter a matrix:");
          for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
              mA[i][j] = sc.nextDouble();
            }
          }
          System.out.println("Enter a constant:");
          double c = sc.nextInt();
          System.out.println("The multiplication result is:");
          multiplyByConstant(mA,c);
          System.out.println();
          break;
        case 3:
          System.out.println("Enter the size of the first matrix: ");
          n = sc.nextInt();
          m = sc.nextInt();
          mA = new double [n][m];
          System.out.println("Enter the first matrix:");
          for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
              mA[i][j] = sc.nextDouble();
            }
          }
          System.out.println("Enter the size of the second matrix: ");
          if(m != sc.nextInt()) {
            System.out.println("ERROR");
            System.out.println();
            sc.nextInt();
            break;
          }
          n = sc.nextInt();
          System.out.println("Enter the second matrix:");
          mB = new double [n][m];
          for(int i = 0; i<m; i++) {
            for(int j = 0; j<n; j++) {
              mB[j][i] = sc.nextDouble();
            }
          }
          System.out.println("The multiplication result is: ");
          multiply(mA, mB);
          System.out.println();
          break;
        case 4:
          System.out.println("1. Main diagonal\r\n" +
              "2. Side diagonal\r\n" +
              "3. Vertical line\r\n" +
              "4. Horizontal line\r\n Your choice: ");
          int choice = sc.nextInt();
          if(choice<1 || choice>4) {
            System.out.println();
            break;
          }
          System.out.println("Enter the size of the matrix: ");
          n = sc.nextInt();
          m = sc.nextInt();
          if(choice<3) {
            if(n!=m) {
              System.out.println("ERROR: The matrix mast be square!");
              System.out.println();
              break;
            }
          }
          mA = new double [n][m];
          System.out.println("Enter the matrix:");
          for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
              mA[i][j] = sc.nextDouble();
            }
          };
          System.out.println("The matrix transposition is: ");
          switch (choice) {
            case 1:
              matrixTranspositionMainDiagonal(mA);
              System.out.println();
              break;
            case 2:
              matrixTranspositionSidediagonal(mA);
              break;
            case 3:
              matrixTranspositionVerticalline(mA);
              break;
            case 4:
              matrixTranspositionHorisontalline(mA);
              break;
            default:
              break;
          }


        default:
          break;
      }

    }
  }

  private static void matrixTranspositionVerticalline(double[][] matrix) {
    for(int i = 0; i<matrix.length; i++) {
      for(int j = 0; j<matrix[0].length; j++) {
        System.out.print(matrix[i][matrix[0].length-1-j]);
        System.out.print(" ");
      }
      System.out.println();
    }
  }

  private static void matrixTranspositionHorisontalline(double[][] matrix) {
    for(int i = 0; i<matrix.length; i++) {
      for(int j = 0; j<matrix[0].length; j++) {
        System.out.print(matrix[matrix.length-1-i][j]);
        System.out.print(" ");
      }
      System.out.println();
    }
  }

  static void matrixTranspositionSidediagonal(double[][] matrix) {
    for(int i = 0; i<matrix.length; i++) {
      for(int j = 0; j<matrix.length; j++) {
        System.out.print(matrix[matrix.length-1-j][matrix.length-1-i]);
        System.out.print(" ");
      }
      System.out.println();
    }

  }

  static void add(double[][] matrixA, double[][] matrixB) {
    for(int i = 0; i<matrixA.length; i++) {
      for(int j = 0; j<matrixA[0].length; j++) {
        System.out.print(matrixA[i][j]+matrixB[i][j]);
        System.out.print(" ");
      }
      System.out.println();
    }
  }

  static void multiplyByConstant(double[][] matrix, double c) {
    for(int i = 0; i<matrix.length; i++) {
      for(int j = 0; j<matrix[0].length; j++) {
        System.out.print(matrix[i][j]*c);
        System.out.print(" ");
      }
      System.out.println();
    }
  }

  static void multiply(double[][] matrixA, double[][] matrixB) {
    double resMatrix = 0;
    for(int i = 0; i<matrixA.length; i++) {
      for(int j = 0; j<matrixB.length; j++) {
        for (int k = 0; k < matrixA[0].length; k++) {
          resMatrix += matrixA[i][k]*matrixB[j][k];
        }
        System.out.print(resMatrix);
        resMatrix = 0;
        System.out.print(" ");
      }
      System.out.println();
    }
  }

  static void matrixTranspositionMainDiagonal(double[][] matrix) {
    for(int i = 0; i<matrix.length; i++) {
      for(int j = 0; j<matrix.length; j++) {
        System.out.print(matrix[j][i]);
        System.out.print(" ");
      }
      System.out.println();
    }
*/
    //stage 3
    /*Scanner sc = new Scanner(System.in);
    int n;
    int m;
    double[][] mA;
    double[][] mB;

    while (true) {
      System.out.println("1. Add matrices\r\n" +
          "2. Multiply matrix to a constant\r\n" +
          "3. Multiply matrices\r\n" +
          "0. Exit\r\n Your choice: ");
      switch (sc.nextInt()) {
        case 0:
          return;
        case 1:
          System.out.println("Enter the size of the first matrix: ");
          n = sc.nextInt();
          m = sc.nextInt();
          mA = new double[n][m];
          System.out.println("Enter the first matrix:");
          for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
              mA[i][j] = sc.nextDouble();
            }
          }
          System.out.println("Enter the size of the second matrix: ");
          if (n != sc.nextInt() || m != sc.nextInt()) {
            System.out.println("ERROR");
            System.out.println();
            sc.nextInt();
            break;
          }
          System.out.println("Enter the second matrix:");
          mB = new double[n][m];
          for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
              mB[i][j] = sc.nextDouble();
            }
          }
          System.out.println("The addition result is: ");
          add(mA, mB);
          System.out.println();
          break;
        case 2:
          System.out.println("Enter the size of a matrix: ");
          n = sc.nextInt();
          m = sc.nextInt();
          mA = new double[n][m];
          System.out.println("Enter a matrix:");
          for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
              mA[i][j] = sc.nextDouble();
            }
          }
          System.out.println("Enter a constant:");
          double c = sc.nextInt();
          System.out.println("The multiplication result is:");
          multiplyByConstant(mA, c);
          System.out.println();
          break;
        case 3:
          System.out.println("Enter the size of the first matrix: ");
          n = sc.nextInt();
          m = sc.nextInt();
          mA = new double[n][m];
          System.out.println("Enter the first matrix:");
          for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
              mA[i][j] = sc.nextDouble();
            }
          }
          System.out.println("Enter the size of the second matrix: ");
          if (m != sc.nextInt()) {
            System.out.println("ERROR");
            System.out.println();
            sc.nextInt();
            break;
          }
          n = sc.nextInt();
          System.out.println("Enter the second matrix:");
          mB = new double[n][m];
          for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
              mB[j][i] = sc.nextDouble();
            }
          }
          System.out.println("The multiplication result is: ");
          multiply(mA, mB);
          System.out.println();
          break;
        default:
          break;
      }

    }
  }

  static void add(double[][] matrixA, double[][] matrixB) {
    for (int i = 0; i < matrixA.length; i++) {
      for (int j = 0; j < matrixA[0].length; j++) {
        System.out.print(matrixA[i][j] + matrixB[i][j]);
        System.out.print(" ");
      }
      System.out.println();
    }
  }

  static void multiplyByConstant(double[][] matrix, double c) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        System.out.print(matrix[i][j] * c);
        System.out.print(" ");
      }
      System.out.println();
    }
  }

  static void multiply(double[][] matrixA, double[][] matrixB) {
    double resMatrix = 0;
    for (int i = 0; i < matrixA.length; i++) {
      for (int j = 0; j < matrixB.length; j++) {
        for (int k = 0; k < matrixA[0].length; k++) {
          resMatrix += matrixA[i][k] * matrixB[j][k];
        }
        System.out.print(resMatrix);
        resMatrix = 0;
        System.out.print(" ");
      }
      System.out.println();
    }*/
   /* //stage 2
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    int[][] matrixA = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        matrixA[i][j] = sc.nextInt();
      }
    }
    int c = sc.nextInt();

    for (int[] integer : matrixA
    ) {
      for (Integer integer1 : integer
      ) {
        System.out.print(integer1 * c);
        System.out.print(" ");
      }
      System.out.println();
    }*/
  }

}
//Stage 1
    /*Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    int[][] matrixA = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        matrixA[i][j] = sc.nextInt();
      }
    }
    if (n != sc.nextInt() || m != sc.nextInt()) {
      System.out.println("ERROR");
      return;
    }
    int[][] matrixB = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        matrixB[i][j] = sc.nextInt();
      }
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        System.out.print(matrixA[i][j] + matrixB[i][j]);
        System.out.print(" ");
      }
      System.out.println();
    }*/
