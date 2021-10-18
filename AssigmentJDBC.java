package hb;

import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AssigmentJDBC {

	public static void main(String[] args) throws IOException, SQLException {
		char choice;
		Scanner scanner = new Scanner(System.in);
		File myObj = new File("./relativePath/toRead.txt");
		Scanner in = new Scanner(myObj);
		final String password = in.nextLine();

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sept", "root", password);
//		Statement sta = con.createStatement();
//		sta.execute("create table emp(no varchar(10),firstname varchar(10),lastname varchar(10),email varchar(100),adress varchar(100),primary key(no))");
//		sta.execute("insert into emp values (101,'Hb','bh','hl@live.com','Ca')");
//		sta.execute("insert into emp values (102,'Hb','bh','hl@live.com','Ca')");
//		sta.execute("insert into emp values (103,'Hb','bh','hl@live.com','Ca')");
//		sta.execute("insert into emp values (104,'Hb','bh','hl@live.com','Ca')");
//		sta.execute("insert into emp values (105,'Hb','bh','hl@live.com','Ca')");
//		sta.execute("insert into emp values (106,'Hb','bh','hl@live.com','Ca')");
//		sta.execute("insert into emp values (107,'Hb','bh','hl@live.com','Ca')");
//		sta.execute("insert into emp values (108,'Hb','bh','hl@live.com','Ca')");
//		sta.execute("insert into emp values (109,'Hb','bh','hl@live.com','Ca')");
//		sta.execute("insert into emp values (110,'Hb','bh','hl@live.com','Ca')");
//		sta.execute("insert into emp values (111,'Hb','bh','hl@live.com','Ca')");
//		sta.execute("insert into emp values (112,'Hb','bh','hl@live.com','Ca')");
//		sta.execute("insert into emp values (113,'Hb','bh','hl@live.com','Ca')");
//		sta.execute("insert into emp values (114,'Hb','bh','hl@live.com','Ca')");
//		sta.execute("insert into emp values (115,'Hb','bh','hl@live.com','Ca')");

		boolean forWhile = true;
		while (forWhile) {
			System.out.println();
			System.out.println("Menu:");
			System.out.println("  1. Insert");
			System.out.println("  2. update");
			System.out.println("  3. delete");
			System.out.println("  4. retrieve by first name or last name ");
			System.out.println("  5. get info of employee by emp no ");
			System.out.println("  6. To exit\n");
			System.out.println("Choose one:");
			System.out.println("\n");
			choice = (char) System.in.read();
			switch (choice) {
			case '1':

				System.out.println("Enter your no: \n");
				String no1 = scanner.next();

				System.out.println("Enter your last name: ");
				String lastName1 = scanner.next();

				System.out.println("Enter your first name: ");
				String firstName1 = scanner.next();

				System.out.println("Enter your email: ");
				String email1 = scanner.next();

				System.out.println("Enter your address: ");
				String adress1 = scanner.next();
				// 2. Create a statement

//
				PreparedStatement st = con.prepareStatement("insert into emp values(?,?,?,?,?)");
//
				// set param values
				st.setString(1, no1);
				st.setString(2, lastName1);
				st.setString(3, firstName1);
				st.setString(4, email1);
				st.setString(5, adress1);

				// 3. Execute SQL query
				st.executeUpdate();
				System.out.println("Added to database :" + no1 + " " + lastName1);

				break;

			case '2':
				PreparedStatement pst = con
						.prepareStatement("update emp set  firstname=?, lastname=?,email=?,adress=? where no=? ");

				System.out.println("Enter employee no that uou want to update : \n");
				String no2 = scanner.next();

				System.out.println("Update your last name : ");
				String lastName2 = scanner.next();

				System.out.println("Update your first name: ");
				String firstName2 = scanner.next();

				System.out.println("Update your email: ");
				String email2 = scanner.next();

				System.out.println("Update your address : ");
				String adress2 = scanner.next();

				pst.setString(1, lastName2);
				pst.setString(2, firstName2);
				pst.setString(3, email2);
				pst.setString(4, adress2);
				pst.setString(5, no2);
				pst.executeUpdate();
				System.out.println("Added to database :" + no2 + " " + lastName2);

				break;
			case '3':

				System.out.println("Enter the no. of the employee you want to delete ");
				String no3 = scanner.next();
				PreparedStatement st1 = con.prepareStatement("delete  from  emp where no=? ");
				st1.setString(1, no3);

				st1.executeUpdate();
				System.out.println("Delete from  database :" + no3);
				break;
			case '4':

				System.out.println("Enter the name or last name  of the employee you want to retrieve ");
				String nameTo = scanner.next();

				PreparedStatement st2 = con.prepareStatement("select * from  emp where lastname=? or firstname=?");

				st2.setString(1, nameTo);
				st2.setString(2, nameTo);
				ResultSet rs = st2.executeQuery();
				while (rs.next()) {
					System.out.println("Employee No :" + rs.getInt(1));
					System.out.println("Employee Name :" + rs.getString("firstname"));
					System.out.println("Employee Address :" + rs.getString("adress"));

				}

				break;
			case '5':
				System.out.println("Enter the no of the employee you want to retrieve ");
				String noTo = scanner.next();

				CallableStatement stNo = con.prepareCall("{call use_this(?)}");

				stNo.setString(1, noTo);

				ResultSet rsNo = stNo.executeQuery();
				while (rsNo.next()) {
					System.out.print("Employee No :" + rsNo.getInt(1) + " \t");
					System.out.print("Employee Name :" + rsNo.getString("firstname") + " \t");
					System.out.print("Employee Last Name :" + rsNo.getString("lastname") + " \t");
					System.out.print("Employee email :" + rsNo.getString("email") + " \t");
					System.out.print("Employee Address :" + rsNo.getString("adress") + " \t");

				}

				break;

			case '6':
				System.out.println("Exited:\n");
				forWhile = false;
				break;
			default:
				System.out.println("Wrong number entered ,try again");
				break;
			}
		}
		scanner.close();
	}

}
