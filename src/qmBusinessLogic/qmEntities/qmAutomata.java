package qmBusinessLogic.qmEntities;

import java.util.Scanner;

public class qmAutomata {
    static final Integer er =-10;
    static final Integer ok =100;
    static final String alfabeto="a,b,c,d,t,..";
    static final Integer[][] mt ={
                                                    {1		, er	, er	, er     , er			, er},
                                                    {er		,2		, er	, er	, er		   ,ok},
                                                    {er		,2		,3		, er	, er		   ,ok},
                                                    {er		, er	, er	, 4		 , er		   ,er},
                                                    {er		, er	, er   , 5		 , 5		   ,er},
                                                    {er   , 5      , 5      , 5       , 5          	,ok}
                                                    };
    @SuppressWarnings("resource")
    private int getIndexAlfabeto(String caracter){
        Scanner scAlfabeto = new Scanner(alfabeto).useDelimiter(" , ");
        for(int i =0; scAlfabeto.hasNext(); i++)
            if(caracter.equals(scAlfabeto.next()))
                return i;
        return er;
    }

    public boolean checkTipoArsenalqm(String arsenal){
        int q=0;
        for(int i =0; i < arsenal.length(); i++){
            int indexAlfa = getIndexAlfabeto(arsenal.charAt(i)+"");
            if(indexAlfa == er || mt[q][indexAlfa].equals(er))
                return false;
            q=mt[q][indexAlfa];
        }
        return (q == ok);
    }
}