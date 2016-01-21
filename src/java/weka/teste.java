/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weka;

/**
 *
 * @author Humberto
 */
public class teste {

    /**
     * @param args the command line arguments
     */
    public static String caminhoDados;

    public static void main(String[] args) throws Exception {

        // Indica onde estão os dados (neste exemplo, eles estão no formato .data)
        caminhoDados = "/C:/Users/Humberto/Desktop/data.arff";
        Weka exemplo1 = new Weka(caminhoDados);

        //exemplo1.criararff();
        exemplo1.leDados();
        exemplo1.imprimeDados();
        exemplo1.JCBA();

        StringBuilder result = new StringBuilder();

        String[] x = exemplo1.JCBA().split("\n");
        for (String i : x) {
            if (i.indexOf("conf:") > 0) {
                try {
                    String p1 = (Float.parseFloat(i.substring(i.indexOf("conf:") + 6, i.indexOf("),"))) * 100) + "%"; // value conf
                    String p2 = i.split(" ")[0]; // value firt part to space 
                    String p3 = i.split(" ")[3]; // value firt part to space 
                    p2 = p2.replace("=", " ");
                    p2 = p2.substring(p2.indexOf("\t") + 1, p2.length());
                    p3 = p3.substring(p3.indexOf(" ") + 1, p3.length());
                    String pr = p1 + " of instances with " + p2;
                    pr += p3.isEmpty() ? "" : " and " + p3;
                    pr += " resulted in system error.";
                    result.append(pr).append("\n");
                } catch (Exception e) {
                    result.append("Line not classified:").append(i).append("\n");
                }
            }
        }

        System.out.println(result.toString());
    }
}
