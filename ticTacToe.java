package gameDevelopment;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ticTacToe extends JFrame implements ActionListener{
public static int board_size=3;
public static enum GameStatus{
	Incomplete, XWins, ZWins ,Tie
}

private JButton[][] buttons = new JButton[board_size][board_size];

boolean crossTurn=true;
	public ticTacToe() {
		super.setTitle("TicTacToe");
		super.setSize(800,800);
		GridLayout grid=new GridLayout(board_size,board_size);
		super.setLayout(grid);
		Font font=new Font("Comic Sans",1 , 150);
		for(int row=0;row<board_size;row++) {
			for(int col=0;col<board_size;col++) {
				JButton button=new JButton("");
				buttons[row][col]=button;
				button.setFont(font);
				button.addActionListener(this);
				super.add(button);
			}
		}
		super.setResizable(false);
		super.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton clickedButton=(JButton)e.getSource();
		makeMove(clickedButton);
		GameStatus gs=this.getGameStatus();
		if(gs==GameStatus.Incomplete) {
			return;
		}
		declareWinner(gs);
		
		int choice=JOptionPane.showConfirmDialog(this, "Do you want to restart the game?");
		if(choice==JOptionPane.YES_OPTION) {
			for(int row=0;row<board_size;row++) {
				for(int col=0;col<board_size;col++) {
					buttons[row][col].setText("");
				}
			}
			crossTurn=true;
		}
		else {
			super.dispose();
		}
	}
	
	private void makeMove(JButton clickedButton) {
		String btnText=clickedButton.getText();
		if(btnText.length()>0) {
			JOptionPane.showMessageDialog(this, "INVALID MOVE");
			
		}
		else {
			if(crossTurn) {
				clickedButton.setText("X");
			}
			else {
				clickedButton.setText("0");
			}
			crossTurn=!crossTurn;
		}
	}
	
	private GameStatus getGameStatus() {
		String text1="";
		String text2="";
		int row=0; int col=0;
		
		//text inside rows
		row=0;
		while(row<board_size) {
			col=0;
			while(col<board_size-1) {
				text1=buttons[row][col].getText();
				text2=buttons[row][col+1].getText();
				
				if(!text1.equals(text2) || text1.length()==0) {
					break;
				}
				col++;
			}
			if(col==board_size-1) {
				if(text1.equals("X")) {
					return GameStatus.XWins;
				}
				else {
					return GameStatus.ZWins;
				}
			}
			row++;
		}
		
		//text inside columns
		col=0;
		while(col<board_size) {
			row=0;
			while(row<board_size-1) {
				text1=buttons[row][col].getText();
				text2=buttons[row+1][col].getText();
				
				if(!text1.equals(text2) || text1.length()==0) {
					break;
				}
				row++;
			}
			if(row==board_size-1) {
				if(text1.equals("X")) {
					return GameStatus.XWins;
				}
				else {
					return GameStatus.ZWins;
				}
			}
			col++;
		}
		
		//text inside diagnol1
		row=0;
		col=0;
		while(row<board_size-1) {
			text1=buttons[row][col].getText();
			text2=buttons[row+1][col+1].getText();
			
			if(!text1.equals(text2) || text1.length()==0) {
				break;
			}
			row++;
			col++;
		}
		if(row==board_size-1) {
			if(text1.equals("X")) {
				return GameStatus.XWins;
			}
			else {
				return GameStatus.ZWins;
			}
		}
		
		//text inside diagnol2
		row=board_size-1;
		col=0;
		while(row>0) {
			text1=buttons[row][col].getText();
			text2=buttons[row-1][col+1].getText();
			
			if(!text1.equals(text2) || text1.length()==0) {
				break;
			}
			row--;
			col++;
		}
		if(row==0) {
			if(text1.equals("X")) {
				return GameStatus.XWins;
			}
			else {
				return GameStatus.ZWins;
			}
		}
		
		String txt="";
		for(row=0;row<board_size;row++) {
			for(col=0;col<board_size;col++) {
				txt=buttons[row][col].getText();
				if(txt.length()==0) {
					return GameStatus.Incomplete;
				}
			}
		}
		
		return GameStatus.Tie;
		
	}
	
	private void declareWinner(GameStatus gs) {
		if(gs==GameStatus.XWins) {
			JOptionPane.showMessageDialog(this, "X Wins");
		}
		else if(gs==GameStatus.ZWins) {
			JOptionPane.showMessageDialog(this, "0 Wins");
		}
		else {
			JOptionPane.showMessageDialog(this, "It is a TIE");
		}
	}
}
