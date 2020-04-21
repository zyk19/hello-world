import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
public class InputMessage extends JPanel implements ActionListener
{
	JPanel panel1,panel2,panel3,panel4,panel5,panel6;
	Hashtable messagetable=null;
	JTextField txlnumber,tname,tphnumber,temail,tdanwei,tzhiwu;
	TXLBasInfor txlbasinfor=null;
	Button input;
	FileInputStream inOne=null;
	ObjectInputStream inTwo=null;
	FileOutputStream outOne=null;
	ObjectOutputStream outTwo=null;
	File file=null;
	public InputMessage(File file)
	{
		this.file=file;
		panel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
		panel4=new JPanel();
		panel5=new JPanel();
		panel6=new JPanel();
		txlnumber=new JTextField(15);
		tname=new JTextField(15);
		tphnumber=new JTextField(15);
		temail=new JTextField(15);
		tdanwei=new JTextField(15);
		tzhiwu=new JTextField(15);
		input=new Button("添加记录");
		input.addActionListener(this);
		panel1.add(new JLabel("序        号"));
		panel1.add(txlnumber);
		panel2.add(new JLabel("姓        名"));
		panel2.add(tname);
		panel3.add(new JLabel("电话号码"));
		panel3.add(tphnumber);
		panel4.add(new JLabel("电子邮箱"));
		panel4.add(temail);
		panel5.add(new JLabel("单        位"));
		panel5.add(tdanwei);
		panel6.add(new JLabel("职        务"));
		panel6.add(tzhiwu);
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
		add(panel5);
		add(panel6);
		add(input);
		validate();
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==input)
		{
			String name="";
			name=tname.getText();
			String number="";
			number=txlnumber.getText();
			
			if(number.length()>0||name.length()>0)
			{
				try{
					inOne=new FileInputStream(file);
					inTwo=new ObjectInputStream(inOne);
					messagetable=(Hashtable)inTwo.readObject();
					messagetable=(Hashtable)inTwo.readObject();
					inOne.close();
					inTwo.close();
				}
				catch(Exception ee){}
				if(messagetable.containsKey(number)||messagetable.containsKey(name))
				{
					String m="信息已存在，新的信息将覆盖原信息！";
					int ok=JOptionPane.showConfirmDialog(this,m,"确认",
					JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
					if(ok==JOptionPane.YES_NO_CANCEL_OPTION)
					{
						record(number,name);
					}
				}
				else
				{
					record(number,name);
				}
			}
			else
			{
				String warning="必须要输入序号或姓名！";
				JOptionPane.showMessageDialog(this,warning,"警告",JOptionPane.WARNING_MESSAGE);
			}
			
		}
	}
	
	public void record(String number,String name)
	{
		txlbasinfor=new TXLBasInfor();
		String phnumber=tphnumber.getText();
		String email=temail.getText();
		String danwei=tdanwei.getText();
		String zhiwu=tzhiwu.getText();
		txlbasinfor.setXlnumber(number);
		txlbasinfor.setName(name);
		txlbasinfor.setPhnumber(phnumber);
		txlbasinfor.setDanwei(danwei);
		txlbasinfor.setEmail(email);
		txlbasinfor.setZhiwu(zhiwu);
		try{
			outOne=new FileOutputStream(file);
			outTwo=new ObjectOutputStream(outOne);
			messagetable.put(number,txlbasinfor);
		//	messagetable.put(name,txlbasinfor);
			outTwo.writeObject(messagetable);
			outTwo.close();
			outOne.close();
			txlnumber.setText(null);
			tname.setText(null);
			tphnumber.setText(null);
			temail.setText(null);
			tdanwei.setText(null);
			tzhiwu.setText(null);
		}
		catch(Exception ee){}
	}
}