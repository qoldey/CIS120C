import java.sql.*; // Use classes in java.sql package

public class JdbcInsertTest { // Save as "JdbcUpdateTest.java"
    public static void main(String[] args) {
        try (
                // Step 1: Allocate a database 'Connection' object
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/southwind?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                        "", ""); // for MySQL only

                // Step 2: Allocate a 'Statement' object in the Connection
                Statement stmt = conn.createStatement();) {
            // Step 3 & 4: Execute a SQL INSERT|DELETE statement via executeUpdate(),
            // which returns an int indicating the number of rows affected.

            // String pwd = System.getProperty("user.dir"); // get current pwd
            // String sqlSource = "source " + pwd + "/dbSetup.sql";
            // System.out.println("The SQL statement is: " + sqlSource + "\n");
            // stmt.execute(sqlSource); //I get told I have an error in my SQL syntax here.
            // Unsure how to run sql scripts from java backend.

            // DELETE records with id >= 1000 for reruns
            String sqlDelete = "delete from products where productID >= 1000";
            System.out.println("The SQL statement is: " + sqlDelete + "\n"); // Echo for debugging
            int countDeleted = stmt.executeUpdate(sqlDelete);
            System.out.println(countDeleted + " records deleted.\n");

            // Setup database
            String sqlInsert = "insert into products values "
                    + "(1001, 'PEN', 'Pen Red', 5000, 1.23),"
                    + "(1002, 'PEN', 'Pen Blue', 8000, 1.25),"
                    + "(1003, 'PEN', 'Pen Black', 2000, 1.25),"
                    + "(1004, 'PEC', 'Pencil 2B', 10000, 0.48),"
                    + "(1005, 'PEC', 'Pencil 2H', 8000, 0.49)";
            System.out.println("The SQL statement is: " + sqlInsert + "\n"); // Echo for debugging
            int countInserted = stmt.executeUpdate(sqlInsert);
            System.out.println(countInserted + " records inserted.\n");

            // Display the every field of every record in the table
            String strSelect = "select * from products";
            System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging
            ResultSet rset = stmt.executeQuery(strSelect);
            ResultSetMetaData rsmd = rset.getMetaData();

            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.print(rsmd.getColumnName(i) + ", ");
            }
            System.out.println();

            while (rset.next()) { // Move the cursor to the next row
                System.out.println(rset.getInt("productID") + ", "
                        + rset.getString("productCode") + ", "
                        + rset.getString("name") + ", "
                        + rset.getInt("quantity") + ", "
                        + rset.getDouble("price"));
            }

            // Display only those rows that have productCode equal to 'PEN'. Select only the
            // name, quantity and price
            strSelect = "select name, quantity, price from products where productCode = 'PEN'";
            System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging
            ResultSet rset2 = stmt.executeQuery(strSelect);
            ResultSetMetaData rsmd2 = rset2.getMetaData();

            for (int i = 1; i <= rsmd2.getColumnCount(); i++) {
                System.out.print(rsmd2.getColumnName(i) + ", ");
            }
            System.out.println();
            while (rset2.next()) { // Move the cursor to the next row
                System.out.println(
                        rset2.getString("name") + ", " + rset2.getInt("quantity") + ", " + rset2.getDouble("price"));
            }

            // Display the productId, productCode, name and quantity of all records with
            // inventory greater than or equal to 7000.
            strSelect = "select productID, productCode, name, quantity from products where quantity >= 7000";
            System.out.println("The SQL statement is: " + strSelect + "\n"); // Echo For debugging
            ResultSet rset3 = stmt.executeQuery(strSelect);
            ResultSetMetaData rsmd3 = rset3.getMetaData();

            for (int i = 1; i <= rsmd3.getColumnCount(); i++) {
                System.out.print(rsmd3.getColumnName(i) + ", ");
            }
            System.out.println();
            while (rset3.next()) { // Move the cursor to the next row
                System.out.println(rset3.getInt("productID") + ", "
                        + rset3.getString("productCode") + ", "
                        + rset3.getString("name") + ", "
                        + rset3.getInt("quantity"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } // Step 5: Close conn and stmt - Done automatically by try-with-resources
    }
}