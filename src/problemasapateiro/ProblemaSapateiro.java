/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package problemasapateiro;
import gurobi.*;
/**
 *
 * @author Jeferson
 */
public class ProblemaSapateiro {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try {
            GRBEnv env = new GRBEnv("Sapateiro.log");
            GRBModel model =  new GRBModel(env);
            
            //variaveis da FO
            GRBVar x = model.addVar(0, GRB.INFINITY, 5, GRB.CONTINUOUS, "Sapato");
            
            GRBVar y = model.addVar(0, GRB.INFINITY, 2, GRB.CONTINUOUS, "Cinto");
            
            //fo
            model.set(GRB.IntAttr.ModelSense, GRB.MAXIMIZE);
            
            /*GRBLinExpr obj = new GRBLinExpr();
            obj.addTerm(5, x); obj.addTerm(2, y);
            model.setObjective(obj, GRB.MAXIMIZE);*/
            
            //restriçoes
            GRBLinExpr expr1 = new GRBLinExpr();
            expr1.addTerm(10, x); expr1.addTerm(12, y);
            model.addConstr(expr1, GRB.LESS_EQUAL, 60, "Restrição de Tempo");
            
            GRBLinExpr expr2 = new GRBLinExpr();
            expr2.addTerm(2, x); expr2.addTerm(1, y);
            model.addConstr(expr2, GRB.LESS_EQUAL, 6, "Restrição de Couro");
            
            model.optimize();
            
            System.out.println(x.get(GRB.StringAttr.VarName) + " " + x.get(GRB.DoubleAttr.X));
            System.out.println(y.get(GRB.StringAttr.VarName) + " " + y.get(GRB.DoubleAttr.X));
            
            System.out.println("Obj: " + model.get(GRB.DoubleAttr.ObjVal));
        } catch (Exception e) {
        }
    }
    
}
