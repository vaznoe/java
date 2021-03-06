package com.company.app;

enum Chemical{Water, Benzene, Ethanol}
enum State{Boiling, Melting}
class ChemicalDatabank{  // Adaptee
    // The databank 'legacy API'
    public float getCriticalPoint(Chemical compound, State point){
        if(point == State.Melting){  // Melting Point
            switch (compound){
                case Water: return 0.0f;
                case Benzene: return 5.5f;
                case Ethanol: return -114.1f;
                default: return 0f;
            }
        }
        else{  // Boiling Point
            switch (compound){
                case Water: return 100.0f;
                case Benzene: return 80.1f;
                case Ethanol: return 78.3f;
                default: return 0f;
            }
        }
    }
    public String getMolecularStructure(Chemical compound){
        switch (compound){
            case Water: return "H20";
            case Benzene: return "C6H6";
            case Ethanol: return "C2H5OH";
            default: return "";
        }
    }
    public double getMolecularWeight(Chemical compound){
        switch (compound){
            case Water: return 18.015;
            case Benzene: return 78.1134;
            case Ethanol: return 46.0688;
            default: return 0d;
        }
    }
}
class Compound{  // Target
    public Chemical chemical;
    public float boilingPoint;
    public float meltingPoint;
    public double molecularWeight;
    public String molecularFormula;
    public void Display(){
        System.out.println("\nCompound: Unknown -------");
    }
}
class RichCompound extends Compound{  // Adapter
    private ChemicalDatabank databank;
    public RichCompound(Chemical chemical){
        this.chemical = chemical;
        databank = new ChemicalDatabank();  // Adaptee
    }
    @Override
    public void Display(){
        boilingPoint = databank.getCriticalPoint(chemical, State.Boiling);
        meltingPoint = databank.getCriticalPoint(chemical, State.Melting);
        molecularWeight = databank.getMolecularWeight(chemical);
        molecularFormula = databank.getMolecularStructure(chemical);

        System.out.format("\nCompound: %s -------- \n", chemical );
        System.out.format("  Formula: %s\n", molecularFormula);
        System.out.format("  Weight: %s\n", molecularWeight);
        System.out.format("  Melting Pt: %s\n", meltingPoint);
        System.out.format("  Boiling Pt: %s\n", boilingPoint);
    }
}
public class App
{
    public static void main( String[] args )
    {
        Compound unknown = new Compound();  // // Non-adapted chemical compound
        unknown.Display();
        RichCompound water = new RichCompound(Chemical.Water);  // Adapted chemical compounds
        water.Display();
        RichCompound benzene = new RichCompound(Chemical.Benzene);
        benzene.Display();
        RichCompound ethanol = new RichCompound(Chemical.Ethanol);
        ethanol.Display();
    }
}
/*

Definition
Convert the interface of a class into another interface clients expect. Adapter lets classes work together that
couldn't otherwise because of incompatible interfaces.

output:
Compound: Unknown -------

Compound: Water --------
  Formula: H20
  Weight: 18.015
  Melting Pt: 0.0
  Boiling Pt: 100.0

Compound: Benzene --------
  Formula: C6H6
  Weight: 78.1134
  Melting Pt: 5.5
  Boiling Pt: 80.1

Compound: Ethanol --------
  Formula: C2H5OH
  Weight: 46.0688
  Melting Pt: -114.1
  Boiling Pt: 78.3
 */
