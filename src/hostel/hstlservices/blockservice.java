package hostel.hstlservices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.mysql.jdbc.Statement;

import hostel.hstlconnection.hstlconnection;
import hostel.hstlmodels.blockmodels;

public class blockservice {
		
	Connection con=hstlconnection.getConnection();
	///////////////////////////////////////////////////////////////////// funcltion for adding blocks.
	public int addblok(blockmodels m){			
		System.out.println("In addblok of block services class ");
		try{
					//////// checking weather record is already in the database.
			PreparedStatement ps=con.prepareStatement("select blok_id from hstlblok where bloknum like ? and blokname like ? and blokstatus like ?");
			ps.setInt(1,m.getBlkNum());
			ps.setString(2, m.getBlkNam());
			ps.setString(3, m.getBlkStat());
			System.out.println(ps);
			
			ps.executeQuery();
			ResultSet rs=ps.getGeneratedKeys();
			if(rs.next())JOptionPane.showMessageDialog(null,"Record exists...!");// if yes print message
			else{					
				ps=con.prepareStatement("insert into hstlblok(bloknum,blokname,blokstatus) values(?,?,?)");// if not then insert record.
			
				ps.setInt(1,m.getBlkNum());
				ps.setString(2, m.getBlkNam());
				ps.setString(3, m.getBlkStat());
				
				ps.executeUpdate();
				rs=ps.getGeneratedKeys();
				if(rs.next()){// if inserted then show message the record is inserted.
					JOptionPane.showMessageDialog(null,"1 Row inserted to database...!");
					return rs.getInt(1);
				}
	
			}
		}
		catch(SQLException sqe){
			System.out.println("Inserting Block data SQL Error occoured. ");
			sqe.printStackTrace();
		}
		catch(Exception e){
			System.out.println("Inserting Block data error " );
			System.out.println("Error occoured. "+e);
		}
		
		return 0;
	}/////end of addblock function.
	
	/////////////////////////////////////////////////////////////////////function for VIEWING datas in the database
	
		public  TableModel rsttbl(){
			try{
					
				System.out.println("In the Result set to Table class");// just message printing
				
				String s="Select * from hstlblok order by blokname";//database query
				
				Statement st=(Statement) con.createStatement();
				ResultSet rs=st.executeQuery(s);
				ResultSetMetaData resMeta=rs.getMetaData();// rsmetadata used to detemine charact: of rs that has been reterived, remetad 
															// is used to retrieve object 
				int numofcolms=resMeta.getColumnCount();//getting number of column 
				Vector<String> colnames=new Vector<String>();
				
				// Get column names
				for(int colms=1;colms<=numofcolms;colms++){
					colnames.addElement(resMeta.getColumnLabel(colms));
				}
				
				// Get all Rows
				Vector<Vector<Object>> row=new Vector<Vector<Object>>();
				
				while(rs.next()){
					Vector<Object> newrow=new Vector<Object>();
					for(int i=1;i<=numofcolms;i++){
						newrow.add(rs.getObject(i));
					}
					row.add(newrow);
				}
				rs.close();
				st.close();
				con.close();
				return new DefaultTableModel(row,colnames) {
					public boolean isCellEditable(int row,int colm) {
						return false;
					}
				};
						
			}
			catch(SQLException se){
				System.out.println("Viewing Block data SQL error " );
				System.err.println("SQl execption Occoured....?");
				JOptionPane.showMessageDialog(null, "No Records to show, Records may be empty");
				return null;
			}
			catch(Exception e){
				System.out.println("Viewing Block data error " );
				System.err.println("Exception occoured...."+e);
			}
			
			return null;
		}///end of method table view or display.
		
		//////////////////////////////////////////////////////////////BLOCK edit ok service
		
		public blockmodels checkBid(blockmodels bm){
			
			System.out.println("IN THE CCCCHECK BLOCK ID METHOD");	
			try{
				PreparedStatement ps=con.prepareStatement("select blok_id from hstlblok where blok_id like ?");/// validating entered blockid
													// if ID is in database
				ps.setInt(1,bm.getBlkId());
				
				ResultSet rs=ps.executeQuery();
				
				if(!rs.next()){
					JOptionPane.showMessageDialog(null, "Wrong Block id entered....!");// if ID not found the show dialog message.
					bm.setFlag(0);
					return bm;
				}
				
				else{/// if found display records.
					ps=con.prepareStatement("select bloknum,blokname,blokstatus from hstlblok where blok_id=?");
					ps.setInt(1,bm.getBlkId());
					rs=ps.executeQuery();
					
					if(rs.next()){
						bm.setBlkNum(rs.getInt(1));
						bm.setBlkNam(rs.getString(2));
						bm.setBlkStat(rs.getString(3));
						bm.setFlag(1);
					}
				}
			}
			catch(SQLException sq){
				System.out.println("SQL Error occured... in Checking blok id");
			}
			catch(Exception e1){
				System.out.println("Error occured... in Checking blok id");
				e1.printStackTrace();
			}
		return bm;
			
		}//////end of edit OK method
		
		////////////////////////////////////////////////////////METHOD FOR DELETING BLOCK DATAS.
		public void blkdel(blockmodels bm){
			int x;
			x=JOptionPane.showConfirmDialog(null,"Are you sure to delete a Record ","Confirm Delete",JOptionPane.YES_NO_OPTION);
			if(x==0){
				try{
					PreparedStatement ps=con.prepareStatement("delete from hstlblok where blok_id=?");
					ps.setInt(1,bm.getBlkId());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "Deleted one Block record...!!!");
				}
				catch(SQLException sq){
					System.out.println("SQL Error from BLKDEL from blockservices. ");
					sq.printStackTrace();
				}
				catch (Exception e) {
					System.out.println(" Error from BLKDEL from blockservices. ");
					e.printStackTrace();
				}
   			}
			else JOptionPane.showMessageDialog(null, "Watch out what you are clicking...!@#@&^*&");
			
		}/////end of delete method
		
		////////////////////////////////////////////////////////////BLOCK UPDATE method...
		public void blkupdate(blockmodels bm){
			try{
				int x;
				System.out.println("In the Block update Service method ");
				x=JOptionPane.showConfirmDialog(null,"Are you sure to Update the Record ","Confirm Delete",JOptionPane.YES_NO_OPTION);
				if(x==0){
					/////////checking record already exists.
					PreparedStatement ps=con.prepareStatement("select blok_id from hstlblok where bloknum like ? and blokname like ?and blokstatus like ?");
					 ps.setInt(1,bm.getBlkNum());
					 ps.setString(2,bm.getBlkNam());
					 ps.setString(3,bm.getBlkStat());
					 
					 ResultSet rs=ps.executeQuery();
					 if(rs.next()){
						JOptionPane.showMessageDialog(null, "Record already exists....");}///if record exists then print message.
					else{
						System.out.println("In the Block update Service method,   checked if already exists, about to update. ");
					  ps=con.prepareStatement("update hstlblok set bloknum=?,blokname=?,blokstatus=? where blok_id=?");
					  ps.setInt(1,bm.getBlkNum());
					  ps.setString(2,bm.getBlkNam());
					  ps.setString(3,bm.getBlkStat());
					  ps.setInt(4,bm.getBlkId());
					  
					  ps.executeUpdate();
					 JOptionPane.showMessageDialog(null, "One Record updated....!");
					 
					}
				}
				else JOptionPane.showMessageDialog(null, "Watch out what you are clicking....!@#@&^*&");
			}
				
			catch(SQLException sq){
				sq.printStackTrace();
			}
			catch(Exception e){
				System.out.println("Error occured"+e);
			}
		}///////End of blk update class.
		
	}/////END OF BLOCKSERVICE CLASS


