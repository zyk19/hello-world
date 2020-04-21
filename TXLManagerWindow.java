import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Hashtable;
public class TXLManagerWindow extends JFrame implements ActionListener
{
	InputMessage inputmessage=null;
	InquestMessage inquestmessage=null;
	Button chaxu;
	Hashtable message=null;
	File file=null;
	public TXLManagerWindow(String s)
	{
		super(s);
		message=new Hashtable();
		chaxu=new Button("查询");
		chaxu.addActionListener(this);
		file=new File("基本信息.txt");
		if(!file.exists())
		{
			try
			{
				FileOutputStream out=new FileOutputStream(file);
				ObjectOutputStream objectOut=new ObjectOutputStream(out);
				objectOut.writeObject(message);
				objectOut.close();
				out.close();
			}
			catch(IOException e){}
		}
		inputmessage=new InputMessage(file);
		inquestmessage=new InquestMessage(this,file);
		Container con=getContentPane();
		con.setLayout(new BorderLayout());
		con.add(inputmessage,BorderLayout.CENTER);
		con.add(chaxu,BorderLayout.SOUTH);
		addWindowListener(new WindowAdapter()
		                  {
		                  	public void windowClosing(WindowEvent e)
		                  	{
		                  		System.exit(0);
		                  	}
		                  });
		setVisible(true);
		setBounds(100,50,250,380);
		validate();
	}
	public void actionPerformed(ActionEvent e)
	{
		inquestmessage.setVisible(true);
	}
}