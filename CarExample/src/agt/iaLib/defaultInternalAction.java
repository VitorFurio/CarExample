package iaLib;

import embedded.mas.bridges.jacamo.EmbeddedAgent;
import src.main.java.*;

import jason.*;
import jason.asSyntax.*;
import jason.asSemantics.*;

public class defaultInternalAction extends DefaultInternalAction {
	@Override
	public Object execute( TransitionSystem ts,Unifier un,Term[] args ) throws Exception {
	
	try {
	
		//Essencialmente as acoes dos projetos embedded-mas apenas enviam uma menssagens para o microcontrolador especificando qual acao o agente quer realizar. Essas mensagens são enviadas por métodos do proprio agente, assim, neste arquivo apenas chamamos estes métodos:
		
		((DemoEmbeddedAgent)ts.getAg()).defaultInternalAction();
		return true;
		
		/*	
		// As acoes tambem podem conter alguma logica, como a de soma abixo:
			// 1. gets the arguments as typed terms
			NumberTerm p1 = (NumberTerm)args[0];
			NumberTerm p2 = (NumberTerm)args[1];
			// 2. calculates the sum
			double r = p1.solve() + p2.solve();
			// 3. creates the term with the result and
			// unifies the result with the 3th argument
			NumberTerm result = new NumberTermImpl(r);
		// E precisam retornar algum valor:
			return un.unifies(result,args[2]);
	*/
	
	}
	catch (ArrayIndexOutOfBoundsException e) {
		throw new JasonException("The internal action" +
		"has not received arguments enought!");
	} 
	catch (ClassCastException e) {
		throw new JasonException("The internal action "+
		"has received arguments that are not numbers!");
	} 
	catch (Exception e) {
		throw new JasonException("Error in internal action");
	}
	}
}
