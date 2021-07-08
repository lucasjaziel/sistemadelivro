package Sistemadelivro;

import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class Sistemadelivro {

	private JFrame frame;
	private JTextField txtnome;
	private JTextField txtedicao;
	private JTextField txtpreco;
	private JTable table;
	private JTextField txtid;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sistemadelivro window = new Sistemadelivro();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Sistemadelivro() {
		initialize();
		Connect();
		table();
	}

	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table_1;
	private JTable table_2;
 
	 public void Connect()
	    {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost/sistemadelivro", "root","");
	        }
	        catch (ClassNotFoundException ex) 
	        {
	          ex.printStackTrace();
	        }
	        catch (SQLException ex) 
	        {
	        	   ex.printStackTrace();
	        }
 
	    }

			  
	  public void table()
		    {
		    	try 
		    	{
			    pst = con.prepareStatement("select * from book");
			    rs = pst.executeQuery();
			    table_2.setModel(DbUtils.resultSetToTableModel(rs));
			} 
		    	catch (SQLException e) 
		    	 {
		    		e.printStackTrace();
			  } 
		    }
		    	   	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 856, 444);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SISTEMA DE LIVRO");
		lblNewLabel.setForeground(new Color(30, 144, 255));
		lblNewLabel.setBackground(Color.GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(258, 22, 241, 36);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(238, 232, 170));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Registrar", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(31, 76, 329, 179);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nome do Livro");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 30, 94, 27);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edi\u00E7\u00E3o");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(10, 78, 94, 27);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Pre\u00E7o");
		lblNewLabel_1_1_1.setBackground(new Color(238, 232, 170));
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1.setBounds(10, 126, 94, 27);
		panel.add(lblNewLabel_1_1_1);
		
		txtnome = new JTextField();
		txtnome.setBounds(142, 34, 142, 20);
		panel.add(txtnome);
		txtnome.setColumns(10);
		
		txtedicao = new JTextField();
		txtedicao.setColumns(10);
		txtedicao.setBounds(142, 82, 142, 20);
		panel.add(txtedicao);
		
		txtpreco = new JTextField();
		txtpreco.setColumns(10);
		txtpreco.setBounds(142, 130, 142, 20);
		panel.add(txtpreco);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.setBackground(UIManager.getColor("Button.background"));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nome, edicao, preco;
				
				nome = txtnome.getText();
				edicao = txtedicao.getText();
				preco = txtpreco.getText();
				
				try {
					pst = con.prepareStatement("insert into book(nome,edicao,preco)values(?,?,?)");
					pst.setString(1, nome);
					pst.setString(2, edicao);
					pst.setString(3, preco);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Adicionado com Sucesso!!!!!");
					table();
						           
					txtnome.setText("");
					txtedicao.setText("");
					txtpreco.setText("");
					txtnome.requestFocus();
				   }
			 
				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				}
				
			}
			
		});		
		
		btnNewButton.setBounds(41, 266, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBackground(UIManager.getColor("Button.background"));
		btnSair.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			System.exit(0);	
				
			
			}
		});
		btnSair.setBounds(139, 266, 89, 23);
		frame.getContentPane().add(btnSair);
		
		JButton btnNewButton_1_1 = new JButton("Limpar");
		btnNewButton_1_1.setBackground(UIManager.getColor("Button.background"));
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				txtnome.setText("");
				txtedicao.setText("");
				txtpreco.setText("");
				txtnome.requestFocus();
					
			
			}
		});
		btnNewButton_1_1.setBounds(238, 266, 89, 23);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(238, 232, 170));
		panel_1.setBorder(new TitledBorder(null, "Procurar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(31, 300, 329, 75);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Cod. Livro");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_2.setBounds(10, 23, 94, 27);
		panel_1.add(lblNewLabel_1_1_2);
		
		txtid = new JTextField();
		txtid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
			          
		            String cod = txtid.getText();

		                pst = con.prepareStatement("select nome,edicao,preco from book where cod = ?");
		                pst.setString(1, cod);
		                ResultSet rs = pst.executeQuery();

		            if(rs.next()==true)
		            {
		              
		                String nome = rs.getString(1);
		                String edicao = rs.getString(2);
		                String preco = rs.getString(3);
		                
		                txtnome.setText(nome);
		                txtedicao.setText(edicao);
		                txtpreco.setText(preco);
		                
		                
		            }   
		            else
		            {
		            	txtnome.setText("");
		            	txtedicao.setText("");
		                txtpreco.setText("");
		                 
		            }
		            
		        } 
			
			 catch (SQLException ex) {
		           
		        }
			
		}
				
			
		});
		txtid.setColumns(10);
		txtid.setBounds(142, 27, 142, 20);
		panel_1.add(txtid);
		
		JButton btnNewButton_1_1_1 = new JButton("Atualizar");
		btnNewButton_1_1_1.setBackground(UIManager.getColor("Button.background"));
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nome, edicao, preco, cod;
				
				nome = txtnome.getText();
				edicao = txtedicao.getText();
				preco = txtpreco.getText();
				cod = txtid.getText();
				
				try {
					pst = con.prepareStatement("update book set nome= ?, edicao= ?, preco= ? where cod =?");
					pst.setString(1, nome);
					pst.setString(2, edicao);
					pst.setString(3, preco);
					pst.setString(4, cod);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Atualizado com Sucesso!!!!!");
					table();
						           
					txtnome.setText("");
					txtedicao.setText("");
					txtpreco.setText("");
					txtnome.requestFocus();
				   }
			 
				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton_1_1_1.setBounds(505, 266, 89, 23);
		frame.getContentPane().add(btnNewButton_1_1_1);
		
		JButton btnNewButton_1_1_1_1 = new JButton("Excluir");
		btnNewButton_1_1_1_1.setBackground(UIManager.getColor("Button.background"));
		btnNewButton_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String cod;
				
				
				cod = txtid.getText();
				
				try {
					pst = con.prepareStatement("delete from book where cod =?");
					pst.setString(1, cod);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Excluido com Sucesso!!!!!");
					table();
						           
					txtnome.setText("");
					txtedicao.setText("");
					txtpreco.setText("");
					txtnome.requestFocus();
				   }
			 
				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				}
				
				
			
			}
		});
		btnNewButton_1_1_1_1.setBounds(612, 266, 89, 23);
		frame.getContentPane().add(btnNewButton_1_1_1_1);
		
		table_1 = new JTable();
		table_1.setBounds(373, 248, -550, 23);
		frame.getContentPane().add(table_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(UIManager.getColor("Button.background"));
		scrollPane.setBounds(370, 76, 427, 179);
		frame.getContentPane().add(scrollPane);
		
		table_2 = new JTable();
		scrollPane.setViewportView(table_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 0));
		panel_2.setBounds(21, 11, 802, 379);
		frame.getContentPane().add(panel_2);
		
		JLabel lblNewLabel_2 = new JLabel("SISTEMA DE LIVRO");
		lblNewLabel_2.setForeground(new Color(30, 144, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBackground(Color.GRAY);
		lblNewLabel_2.setBounds(484, 16, 197, 43);
		frame.getContentPane().add(lblNewLabel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 0, 820, 405);
		frame.getContentPane().add(panel_3);
	}
}
