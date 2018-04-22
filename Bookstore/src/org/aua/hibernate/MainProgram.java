package org.aua.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.aua.hibernate.entity.*;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainProgram {

	public static void main(String[] args) {
		
		System.out.println("Hibernate project");
		 
        Session session = HibernateUtilities.getSessionFactory().openSession();
        
		
        MainProgram program = new MainProgram();
	        
        program.init(session);
        
        // stock for each book, store_id - quantity
        Map<Integer, Integer> stock = new HashMap<Integer, Integer>();
        stock.put(1, 20);
        stock.put(2, 10);
        stock.put(3, 10);
        
        //add new books
        program.addBook(session, "The A.B.C. Murders", "Crime", "Agatha Christie", 6000, stock);
        program.addBook(session, "Three Blind Mice", "Crime", "Agatha Christie", 6000, stock);
        program.addBook(session, "Witness for the Prosecution", "Crime", "Agatha Christie", 6000, stock);
        program.addBook(session, "To Kill a Mockingbird", "Novel", "Harper Lee", 7000, stock);
        program.addBook(session, "Animal Farm", "Novel", "George Orwell", 5000, stock);
        
        //program.listCategories(session);
        
        program.setDiscount(session, 1, 1);
        
        program.buyBook(session, 2, 3, 1 );
        program.buyBook(session, 1, 3, 1 );
        
        program.buyBook(session, 5, 4, 0 );
        program.buyBook(session, 5, 4, 0 );
        program.buyBook(session, 5, 4, 2 );
        program.buyBook(session, 5, 4, 2 );
        
        session.close();

	}
	
	public void init(Session session) {
		Transaction tx = null;
        try {
    	    tx = session.beginTransaction();
    	    
    	    Store store1 = new Store("19 Abovyan St, Yerevan 0001, Armenia");
    	    session.save(store1);
    	    Store store2 = new Store("4 North Ave, Yerevan 0001, Armenia");
    	    session.save(store2);
    	    Store store3 = new Store("10 Yeznik Koghbatsi St, Yerevan 0010, Armenia");
    	    session.save(store3);
    	    
    	    Employee employee1 = new Employee("Jack Black", 120000, store1);
    	    session.save(employee1);
    	    Employee employee2 = new Employee("Meriam White", 120000, store1);
    	    session.save(employee2);
    	    Employee employee3 = new Employee("Ann Brown", 120000, store2);
    	    session.save(employee3);
    	    Employee employee4 = new Employee("Antony Green", 120000, store3);
    	    session.save(employee4);
    	    
    	    Contact employee1_contact1 = new Contact(employee1, "099-22-22-22");
    	    session.save(employee1_contact1);
    	    Contact employee1_contact2 = new Contact(employee1, "099-55-55-22");
    	    session.save(employee1_contact2);
    	    Contact employee4_contact1 = new Contact(employee4, "099-44-45-22");
    	    session.save(employee4_contact1);
    	    
    	    Customer customer1 = new Customer();
    	    customer1.setPoints(20);
    	    session.save(customer1);
    	    Customer customer2 = new Customer();
    	    session.save(customer2);
    	    
    	    Discount discount1 = new Discount(20, 50);
    	    session.save(discount1);
    	    Discount discount2 = new Discount(5, 5);
    	    session.save(discount2);
    	    
            tx.commit();
        }
	    catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }
   }
	
	public void getNumber(Session session) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List result = (List) session.createQuery("SELECT COUNT(name) FROM Category").list(); 
			System.out.print("Number of categories: " + result.get(0).toString()); 
	        tx.commit();
	      }
	      catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }
   }
	
	public void addBook(Session session, String title, String category, String author, int price, Map<Integer, Integer> stock) {
		Transaction tx = null;
        Integer categoryID = null;
        try {
    	    tx = session.beginTransaction();
    	    
    	    Query query = session.createQuery("SELECT COUNT(*) FROM Category WHERE name = :name"); 
    	    query.setParameter("name", category);
    	    List result1 = query.list();
    	    
    	    if (Integer.parseInt(result1.get(0).toString()) == 0)
    	    {
    	    	System.out.println("Entering new category"); 
    	    	Category newcateg = new Category(category);
    	    	session.save(newcateg); 
    	    }
    	    
    	    Query query2 = session.createQuery("SELECT COUNT(*) FROM Author WHERE full_name = :name"); 
    	    query2.setParameter("name", author);
    	    List result2 = query2.list();
    	    
    	    if (Integer.parseInt(result2.get(0).toString()) == 0)
    	    {
    	    	System.out.println("Entering new author"); 
    	    	Author newauthor = new Author(author, "");
    	    	session.save(newauthor); 
    	    }
    	    
    	    Query query3 = session.createQuery("FROM Category WHERE name = :name"); 
    	    query3.setParameter("name", category);
    	    List categorylist = query3.list();
    	    Category categoryobj = (Category) categorylist.get(0);
    	    
    	    Query query4 = session.createQuery("FROM Author WHERE full_name = :name"); 
    	    query4.setParameter("name", author);
    	    List authorlist = query4.list();
    	    Author auhtorobj = (Author) authorlist.get(0);
    	    
    	    
    	    Book book = new Book(title, price, categoryobj, auhtorobj);
    	    System.out.println("Saving new book"); 
    	    session.save(book); 
    	    
    	    for(Map.Entry<Integer, Integer> entry : stock.entrySet())
    	    {
    	    	//stocking books
    	    	System.out.println("Stocking book for store " + entry.getKey()); 
    	    	Query query5 = session.createQuery("FROM Store WHERE id = :id"); 
        	    query5.setParameter("id", entry.getKey());
        	    List storelist = query5.list();
        	    Store storeobj = (Store) storelist.get(0);
    	        
    	    	InStock instock = new InStock(book, storeobj, entry.getValue());
    	    	session.save(instock); 
    	    }
    	    
            tx.commit();
        }
	    catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }
   }
	
	public int buyBook(Session session, int bookId, int employeeId, int customerId ) {
		Transaction tx = null;
        int value = 0;
        try {
    	    tx = session.beginTransaction();
    	    
    	    Query query1 = session.createQuery("FROM Book WHERE book_id = :id"); 
    	    query1.setParameter("id", bookId);
    	    List booklist = query1.list();
    	    Book bookobj = (Book) booklist.get(0);
    	    value = bookobj.getPrice();

    	    Query query2 = session.createQuery("FROM Employee WHERE employee_id = :id"); 
    	    query2.setParameter("id", employeeId);
    	    List employeelist = query2.list();
    	    Employee employeeobj = (Employee) employeelist.get(0);
    	    
    	    Sale sale = new Sale(bookobj, employeeobj, value);
    	    System.out.println("Saving new sale"); 
    	    if(customerId != 0)
    	    {
    	    	//find customer and set
    	    	Query query4 = session.createQuery("FROM Customer WHERE customer_id = :id"); 
        	    query4.setParameter("id", customerId);
        	    List customerlist = query4.list();
        	    Customer customerobj = (Customer) customerlist.get(0);
        	    
        	    int points = customerobj.getPoints();
        	    customerobj.setPoints(points + value/ 100);
        	    
        	    Discount discount = bookobj.getDiscount();
        	    
        	    if(discount != null)
        	    {
        	    	if(discount.getPointMargin() < points)
        	    	{
        	    		value -= discount.getPercentage() * value / 100;
        	    	}
        	    }
        	    
        	    sale.setCustomer(customerobj);
    	    }
    	    
    	    sale.setValue(value);
    	    session.save(sale);
    	    
    	    System.out.println("Updating stock"); 
    	    Query query5 = session.createQuery("FROM InStock WHERE book_id = :id_b AND store_id = :id_s"); 
    	    query5.setParameter("id_s", employeeobj.getStrore().getId());
    	    query5.setParameter("id_b", bookobj.getId());
    	    List stock_list = query5.list();
    	    InStock stock_obj = (InStock) stock_list.get(0);
    	    int quantity = stock_obj.getQuantity();
    	    stock_obj.setQuantity(quantity - 1);
    	    
            tx.commit();
        }
	    catch (HibernateException e) {
           if (tx!=null) tx.rollback();
           e.printStackTrace(); 
        }
        return value;
   }
	
	public void setDiscount(Session session, int bookId, int discountId) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
	    	
    	    Query query2 = session.createQuery("FROM Discount WHERE discount_id = :id"); 
    	    query2.setParameter("id", discountId);
    	    List discount_list = query2.list();
    	    Discount discount = (Discount) discount_list.get(0);
    	    
    	    Query query1 = session.createQuery("FROM Book WHERE book_id = :id"); 
    	    query1.setParameter("id", bookId);
    	    List book_list = query1.list();
    	    Book book_obj = (Book) book_list.get(0);
    	    book_obj.setDiscount(discount);
	    	    
	        tx.commit();
	      }
	      catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }
	}
	
	public void listCategories(Session session) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List result = (List) session.createQuery("FROM Category").list(); 
			for (Category category : (List<Category>) result) { 
				System.out.print("ID:  " + category.getId() + "     "); 
				System.out.print("Name:  " + category.getName() + "\n"); 
			}
	        tx.commit();
	      }
	      catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }
	}
}
