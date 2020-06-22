package processor;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    //stage 3
    Scanner sc = new Scanner(System.in);
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
    }
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
