package dataAccess;

import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.ApustuAnitza;
import domain.Registered;

public class RegisteredAdapter extends AbstractTableModel {

	

	private String[] columnNames = {"Event", "Question", "Event Date", "Bet(€)"};

	Registered user;

	List<ApustuAnitza> apuestasMultiples;



	public RegisteredAdapter(Registered user) {
		this.user = user;
		this.apuestasMultiples=user.getApustuAnitzak();
	}


	@Override
	public int getRowCount() {
		if (this.apuestasMultiples==null)
			return 0;
		else
			return apuestasMultiples.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int row, int col) {

		Object temp = null;
		
		if (apuestasMultiples.get(row).getApustuak().size()>0) {
			
			if (col == 0) {
				temp = apuestasMultiples.get(row).getApustuak().firstElement().getKuota().getQuestion().getEvent().getDescription();
			}
			else if (col == 1) {
				temp = apuestasMultiples.get(row).getApustuak().firstElement().getKuota().getQuestion().getQuestion();

			}
			else if (col == 2) {
				temp = apuestasMultiples.get(row).getApustuak().firstElement().getKuota().getQuestion().getEvent().getEventDate();
			}
			else if (col ==3) {
				temp = apuestasMultiples.get(row).getBalioa();
			}
			return temp;	
		}
		else
			return null;
		
		
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}


	public void imprimirApuestas() {
		for (ApustuAnitza a: apuestasMultiples) {
			if (a.getApustuak().size()>0) {
				System.out.println(a.getApustuak().firstElement().getKuota().getQuestion().getEvent().getDescription()+",  "
			+a.getApustuak().firstElement().getKuota().getQuestion().getQuestion()+", "
			+a.getApustuak().firstElement().getKuota().getQuestion().getEvent().getEventDate()+", "
			+a.getBalioa());
			}
		}
		
	}
	
	public Class getColumnClass(int col) {
		if (col == 0 || col == 1) {
			return String.class;
		}
		else if (col == 2) {
			return Date.class;
		}
		else{
			return Double.class;
		}
	}

}
