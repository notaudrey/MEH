package us.plxhack.MEH.UI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.zzl.minegaming.GBAUtils.BitConverter;
import org.zzl.minegaming.GBAUtils.DataStore;
import org.zzl.minegaming.GBAUtils.ROMManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DNPokePatcher extends JFrame
{
	private JTextField txtFreespace;
	private JTextField txtStatusByte;
	final JLabel lblError;
	final JButton btnPatch;
	final JButton btnCancel;
	
	byte frPatch[] = {
			(byte)0x00, (byte)0x1B, (byte)0x11, (byte)0x4A, (byte)0x12, (byte)0x78,
			(byte)0x12, (byte)0x49, (byte)0x8A, (byte)0x5C, (byte)0x82, (byte)0x18,
			(byte)0x01, (byte)0xE0, (byte)0x02, (byte)0x1C, (byte)0x10, (byte)0x32,
			(byte)0xD1, (byte)0x78, (byte)0xFF, (byte)0x29, (byte)0x12, (byte)0xD0,
			(byte)0x08, (byte)0x29, (byte)0x00, (byte)0xD1, (byte)0x10, (byte)0x68,
			(byte)0x20, (byte)0x18, (byte)0x04, (byte)0x1C, (byte)0x60, (byte)0x78,
			(byte)0x21, (byte)0x78, (byte)0x88, (byte)0x42, (byte)0x01, (byte)0xD3,
			(byte)0x25, (byte)0x78, (byte)0x01, (byte)0xE0, (byte)0x65, (byte)0x78,
			(byte)0x20, (byte)0x78, (byte)0x44, (byte)0x1B, (byte)0x01, (byte)0x34,
			(byte)0x24, (byte)0x06, (byte)0x24, (byte)0x0E, (byte)0x04, (byte)0x4A,
			(byte)0x10, (byte)0x47, (byte)0x12, (byte)0x1A, (byte)0x0C, (byte)0x2A,
			(byte)0xE4, (byte)0xD0, (byte)0x02, (byte)0x1C, (byte)0xE4, (byte)0xE7,
			(byte)0x00, (byte)0xC0, (byte)0x03, (byte)0x02, (byte)0x15, (byte)0x29,
			(byte)0x08, (byte)0x08, (byte)0x54, (byte)0x00, (byte)0x80, (byte)0x08,
			(byte)0x0C, (byte)0x00, (byte)0x04, (byte)0x08, (byte)0x0C, (byte)0x0C,
			(byte)0xC0, (byte)0x46, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
			(byte)0x78, (byte)0x68, (byte)0x01, (byte)0xB4, (byte)0x27, (byte)0x48,
			(byte)0x00, (byte)0x28, (byte)0x05, (byte)0xD0, (byte)0x20, (byte)0x48,
			(byte)0x00, (byte)0xF0, (byte)0x2B, (byte)0xF8, (byte)0x00, (byte)0x88,
			(byte)0x01, (byte)0x28, (byte)0x17, (byte)0xDA, (byte)0x01, (byte)0xBC,
			(byte)0x04, (byte)0xB4, (byte)0x1A, (byte)0x4A, (byte)0x12, (byte)0x78,
			(byte)0x1E, (byte)0x49, (byte)0x8A, (byte)0x5C, (byte)0x82, (byte)0x18,
			(byte)0x01, (byte)0xE0, (byte)0x02, (byte)0x1C, (byte)0x10, (byte)0x32,
			(byte)0xD1, (byte)0x78, (byte)0xFF, (byte)0x29, (byte)0x1F, (byte)0xD0,
			(byte)0x08, (byte)0x29, (byte)0x00, (byte)0xD1, (byte)0x10, (byte)0x68,
			(byte)0x20, (byte)0x18, (byte)0x40, (byte)0x88, (byte)0x11, (byte)0x49,
			(byte)0x08, (byte)0x80, (byte)0x29, (byte)0x1C, (byte)0x04, (byte)0xBC,
			(byte)0x0E, (byte)0x4B, (byte)0x18, (byte)0x47, (byte)0x01, (byte)0xBC,
			(byte)0x11, (byte)0x48, (byte)0x00, (byte)0xF0, (byte)0x0C, (byte)0xF8,
			(byte)0x82, (byte)0x1E, (byte)0x10, (byte)0x49, (byte)0x09, (byte)0x68,
			(byte)0x09, (byte)0x79, (byte)0x8B, (byte)0x79, (byte)0x1B, (byte)0x02,
			(byte)0xC9, (byte)0x18, (byte)0x12, (byte)0x88, (byte)0x91, (byte)0x42,
			(byte)0x03, (byte)0xD1, (byte)0x00, (byte)0x88, (byte)0xE9, (byte)0xE7,
			(byte)0x08, (byte)0x49, (byte)0x08, (byte)0x47, (byte)0x78, (byte)0x68,
			(byte)0x01, (byte)0xB4, (byte)0xD4, (byte)0xE7, (byte)0x12, (byte)0x1A,
			(byte)0x0C, (byte)0x2A, (byte)0xD7, (byte)0xD0, (byte)0x02, (byte)0x1C,
			(byte)0xD7, (byte)0xE7, (byte)0xC0, (byte)0x46, (byte)0x51, (byte)0x2B,
			(byte)0x08, (byte)0x08, (byte)0x5C, (byte)0x55, (byte)0x00, (byte)0x03,
			(byte)0x00, (byte)0xC0, (byte)0x03, (byte)0x02, (byte)0x55, (byte)0xE4,
			(byte)0x06, (byte)0x08, (byte)0xFF, (byte)0x4F, (byte)0x00, (byte)0x00,
			(byte)0xFE, (byte)0x4F, (byte)0x00, (byte)0x00, (byte)0x08, (byte)0x50,
			(byte)0x00, (byte)0x03, (byte)0xFC, (byte)0x00, (byte)0x80, (byte)0x08,
			(byte)0x0C, (byte)0x00, (byte)0x04, (byte)0x08, (byte)0x0C, (byte)0x0C,
			(byte)0xC0, (byte)0x46, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00
		};
	private int frLevelLoc = 0x0828FA;
	private int frPkmnLoc = 0x082B48;
	
	private byte[] frLevelPatch = {0x1, 0x4A, 0x10, 0x47, 0, 0, 0,0,0,0};
	private byte[] frPkmnPatch = {0,0x49,0x08,0x47,0,0,0,0};
	
	private boolean successfulPatch = false;
	public DNPokePatcher()
	{		
		setResizable(false);
		setTitle("Day/Night Pokemon Patcher");
		this.setSize(358, 237);
		getContentPane().setLayout(null);
		
		btnPatch = new JButton("Patch!");
		btnPatch.setBounds(252, 166, 81, 25);
		getContentPane().add(btnPatch);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(false);
				dispose();
			}
		});
		btnCancel.setBounds(159, 166, 81, 25);
		getContentPane().add(btnCancel);
		
		JLabel lblThisDialogWill = new JLabel("<html><center>This dialog will patch in support for Day/Night Pokemon. If you aren't sure what values to use just leave them as the defaults or check with primal's DNS tool.</center></html>");
		lblThisDialogWill.setBounds(12, 12, 340, 60);
		getContentPane().add(lblThisDialogWill);
		
		txtFreespace = new JTextField();
		txtFreespace.setBounds(159, 94, 114, 19);
		getContentPane().add(txtFreespace);
		txtFreespace.setColumns(10);
		
		JLabel lblFreespaceLocation = new JLabel("Freespace Location:");
		lblFreespaceLocation.setBounds(12, 96, 144, 15);
		getContentPane().add(lblFreespaceLocation);
		
		JLabel lblStatusByteLocation = new JLabel("Status Byte RAM:");
		lblStatusByteLocation.setBounds(12, 123, 144, 15);
		getContentPane().add(lblStatusByteLocation);
		
		txtStatusByte = new JTextField();
		txtStatusByte.setText("0203C000");
		txtStatusByte.setBounds(159, 125, 114, 19);
		getContentPane().add(txtStatusByte);
		txtStatusByte.setColumns(10);
		
		lblError = new JLabel("");
		lblError.setBounds(22, 142, 119, 69);
		getContentPane().add(lblError);
		
		btnPatch.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(successfulPatch)
				{
					setVisible(false);
					dispose();
				}
				
				int freespace;
				int status;
				try
				{
					freespace = Integer.parseInt(txtFreespace.getText(),16);
				}
				catch(Exception ex)
				{
					lblError.setForeground(Color.RED);
					lblError.setText("<html><center>Invalid Freespace Specified!</center></html>");
					return;
				}
				
				try
				{
					status = Integer.parseInt(txtStatusByte.getText(),16);
				}
				catch(Exception ex)
				{
					lblError.setForeground(Color.RED);
					lblError.setText("<html><center>Invalid Status RAM Specified!</center></html>");
					return;
				}
				
				if(patchROM(freespace, status))
				{
					lblError.setForeground(Color.green);
					lblError.setText("<html><center>Success!</center></html>");
					btnCancel.setVisible(false);
					btnPatch.setText("Close");
					txtFreespace.setEditable(false);
					txtStatusByte.setEditable(false);
					successfulPatch = true;
				}
			}
		});
		
		if(ROMManager.getActiveROM() == null)
		{
			this.setVisible(false);
			this.dispose();
		}
		else
		{
			txtFreespace.setText("" + Integer.toHexString(ROMManager.getActiveROM().findFreespace(0x108, (int)DataStore.FreespaceStart, true)));
		}
	}
	
	private boolean patchROM(int freespace, int status)
	{
		if(!ROMManager.getActiveROM().getGameCode().equalsIgnoreCase("BPRE"))
		{
			lblError.setForeground(Color.RED);
			lblError.setText("<html><center>Invalid ROM!<br/>Only Fire Red is currently supported!</center></html>");
			
			btnCancel.setVisible(false);
			btnPatch.setText("Close");
			txtFreespace.setEditable(false);
			txtStatusByte.setEditable(false);
			successfulPatch = true;
			return false;
		}
		if((freespace & 0xF) != 0x0 && (freespace & 0xF) != 0x4 && (freespace & 0xF) != 0x8 && (freespace & 0xF) != 0xC)
		{
			lblError.setForeground(Color.RED);
			lblError.setText("<html><center>Freespace offset must end in 0, 4, 8, or C!</center></html>");
			return false;
		}
		frPatch = BitConverter.PutBytes(frPatch, BitConverter.ReverseBytes(BitConverter.GetBytes(freespace+0x54+0x08000000)), 0x50);
		frPatch = BitConverter.PutBytes(frPatch, BitConverter.ReverseBytes(BitConverter.GetBytes(freespace+0xFC+0x08000000)), 0xF8);
		
		frPatch = BitConverter.PutBytes(frPatch, BitConverter.ReverseBytes(BitConverter.GetBytes(status)), 0x48);
		frPatch = BitConverter.PutBytes(frPatch, BitConverter.ReverseBytes(BitConverter.GetBytes(status)), 0xE4);
		
		frLevelPatch = BitConverter.PutBytes(frLevelPatch, BitConverter.ReverseBytes(BitConverter.GetBytes(freespace+1+0x08000000)), 0x6);
		frPkmnPatch = BitConverter.PutBytes(frPkmnPatch, BitConverter.ReverseBytes(BitConverter.GetBytes(freespace+0x60+1+0x08000000)), 0x4);
		
		ROMManager.getActiveROM().writeBytes(freespace,frPatch);
		ROMManager.getActiveROM().writeBytes(frLevelLoc, frLevelPatch);
		ROMManager.getActiveROM().writeBytes(frPkmnLoc, frPkmnPatch);
		
		return true;
	}
}