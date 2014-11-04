/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MainPackage;
import Jama.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 *  Joseph Zhong
 *  ASSIGNMENT_NUMBER
 *  PROGRAM_DESCRIPTION
 *  PROGRAM_TITLE
 *  DATE
 *
 **/

public class mainMethods
{
    private Matrix XMatrix;
    private Matrix YMatrix;

    private Matrix CoefficientMatrix;
    private static int d;

    public static void main(String[] args) throws FileNotFoundException
    {
        //File input = new File("C:/Users/Joseph/Downloads/GitHub/PolynomialRegression5/src/MainPackage/bestfit_dataset_example.txt");

        // Set up reader
        Scanner scn = new Scanner(new File("C:/Users/Joseph/Downloads/GitHub/PolynomialRegression5/src/MainPackage/bestfit_dataset_example.txt"));
        Scanner userScn = new Scanner(System.in);
        // Set up containers
        /*
        double xValues[];
        double yValues[];
        * */
        ArrayList<Double> xValues = new ArrayList<>();
        ArrayList<Double> yValues = new ArrayList<>();

        // X and Y Value input
        while(scn.hasNextDouble())
        {
            xValues.add(scn.nextDouble());
            yValues.add(scn.nextDouble());

            scn.nextLine();
        }

        // d Input sanitation
        d = 0;
        while(!(d <= 4 && d >= 1))
        {
            input(userScn);
        }

        /*
        for(double i : xValues)
        {
            System.out.print(i + " ");
        }
        System.out.println();
        for(double j : yValues)
        {
            System.out.print(j + " ");
        }
        */
        populateXMatrix(xValues);
    }

    private static void input(Scanner userScn)
    {
        System.out.print("Degree value of polynomial? (1-4): ");

        if(!userScn.hasNextInt())
        {
            userScn.next();
            inputError();
        }
        else if((d = userScn.nextInt()) > 4 || d < 1)
        {
            inputError();
        }
        else
        {
            //d = userScn.nextInt();
            System.out.println("Your polynomial degree is: " + d);
        }
        /*
        while(!userScn.hasNextInt())
        {
            inputError();
        }*/

    }

    private static void inputError()
    {
        System.out.println("Please enter an integer value from 1 to 4.");
        //input(userScn);
    }

    private static void populateXMatrix(ArrayList<Double> _xValues)
    {
        double[][] XArray = new double[d+1][d+1];
        for(int c = 0; c < XArray.length; c++)
        {
            for(int r = c; r < XArray[c].length; r++)
            {
                XArray[r][c] = XArray[c][r] = SumX(2*d-r-c, _xValues);
            }
        }
    }

    private static double SumX(int degree, ArrayList<Double> _x)
    {
        double sum = 0.;
        for(Double x : _x)
        {
            sum += Math.pow(x, degree);
        }
        return sum;
    }
}
