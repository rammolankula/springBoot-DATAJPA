package init.learn.runner;


import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Example;

import init.learn.model.Product;
import init.learn.repo.Productrepo;
//import init.learn.repo.Productrepo.Myview;
//import init.learn.repo.Productrepo.Myview;
//@Component
public class Productrunner implements CommandLineRunner {
    
	@Autowired
	private Productrepo repo;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("run..............");
		// TODO Auto-generated method stub
		
	//	This is for saving data in database using CommandLinRunner
		repo.save(new Product(1,"ABC", 2.0,"Rm","huhu",new Date(), true));
		repo.save(new Product(2,"CDE", 2.0,"Gnesh","huhu", new Date(),false));
		repo.save(new Product(3,"EFG", 2.0,"Sham","huhu", new Date(),false));
		repo.save(new Product(4,"HIJ", 2.0,"pavan","huhu", new Date(),true)); 
		
	/*   This For getting the data by ID,by Name using "Optional" */
	 	Optional<Product> opt=repo.findById(1);
		if(opt.isPresent()) {
		Product p=opt.get();	
		System.out.println(p);
		}else {
			System.out.println("does not exist");
		}      
	/* find  spceified	rows using examples */
     	Product obj=new Product();
		obj.setProductCost(2.0);
		Example<Product> example=Example.of(obj);
		repo.findAll(example).forEach(System.out::println);
		
		
		
    /* The product what i specified here is "huhu" is finded in Repository "ProductVendor" */
		repo.findByProductVendor("huhu").forEach(System.out::println);
	
	  
		/* Here iam going to get the data which i productcost is notnull  related  */
		repo.findByProductCostIsNotNull().forEach(System.out::println);
	    
		 
		/*For deleting All at a time */
		repo.deleteAllInBatch();
		
		
		/*Here iam going to get data which is nullin product cost */
	     repo.findByProductCostIsNull().forEach(System.out::println);	
	     
	
/*Here iam finding th data which is morethan 100.0 using findBy GreaterThan=100.0 */
		repo.findByProductCostGreaterThan(100.0).forEach(System.out::println);
	     
	
/*Here iam finding the  data which is equals to the 250.0 using by findbyroductcostEquals to*/
        repo.findByProductCostEquals(250.0).forEach(System.out::println);
	
/*Here iam finding the data which is starts with "P" using by findByProductVendorStartingWith */
	repo.findByProductVendorStartingWith("P").forEach(System.out::println);
	
	
	
/*Here iam finding the data which is EndingWith with "y" using by findByProductVendorEndingWith */
	repo.findByProductVendorEndingWith("y").forEach(System.out::println);
    	
	  
/*Here iam finding the data which is Conataining  "e" using by findByProductVendorContaining */
	repo.findByProductVendorIsContaining("e").forEach(System.out::println);
	
	
/*Here iam finding the data which is starts with "P" using like  operater */
	repo.findByProductVendorLike("P%").forEach(System.out::println);
	
		
/*Here iam finding the data which is ends with "n" using like  operater */
	repo.findByProductVendorLike("%n").forEach(System.out::println);
   
		
/*Here iam finding the data which is the word middele  "e" using like  operater */
	repo.findByProductVendorLike("%e%").forEach(System.out::println);
    
		
/*Here iam finding the data which is the word order using 
     repo.findByProductIOrderByProductCodeDesc().forEach(System.out::println);
	*/
		
	/*Here iam finding the data which is "Between"  some particular value to another particular value  */ 
	repo.findByProductCostBetween(100,255).forEach(System.out::println);	
	
    
	/*Here iam finding the data which is "Or" 	one  or  another */
	repo.findByProductIdOrProductCode(2,"GHI").forEach(System.out::println);	
	
		
	/*Here iam finding the data which is "NotOr" 	one  or  another */
	repo.findByProuductIdNotOrProductCost(2,"GHI").forEach(System.out::println);
		
		
	/*Here iam finding the data which i want the random data like ProductId in ProductCostIn	*/
	repo.findByProductIdIn(Arrays.asList(2,3,11)).forEach(System.out::println);
		
		
    //Here iam giving the represent date of the product using "Date" predefined and Boolean True and False
	repo.save(new Product(1,"ABC",100.0,"PEN","Use",new Date(System.currentTimeMillis()),false));
	repo.save(new Product(2,"CDE",199.0,"Box","Use",new Date(System.currentTimeMillis()),true));
	repo.save(new Product(3,"EFG",200.0,"XOB","use",new Date(System.currentTimeMillis()),false));
	repo.save(new Product(4,"GHI",201.0,"pant","use",new Date(System.currentTimeMillis()),true));
	
		
	/* gettig the data which is True i data base */
   repo.findByStatusTrue().forEach(System.out::println);
   

	
	/*getting the data which  is false in the database */
   repo.findByStatusFalse().forEach(System.out::println);
    	
		
	/*getting the data which is before the date*/
	repo.findByMfgDateBefore(new Date(System.currentTimeMillis())).forEach(System.out::println);
	
	
	/*getting the data which is After the date */
	repo.findByMfgDateAfter(new Date(System.currentTimeMillis())).forEach(System.out::println);
	
	
	/*getting the data which is current date */
	repo.findByCurrentDate(new  Date(System.currentTimeMillis())).forEach(System.out::println);
	
	
	/*projections is nothing but fetching the required columns*/
	/*static projections---onece columns and variables are prrovided then everytime it will display's*/
	
 /*	List<Myview> data=repo.findByProductVendor("USE");
	for(Myview  m:data) {
		System.out.println(m.getProductCode()+" "+m.getProductCost()+""+m.getProductId());
	*/
	
	/*Dynamic projection it can specify the view type while executing the query 
	List<Myview> data=repo.findByProductVendor("USE",Myview.class);
	for(Myview  m:data) {
		System.out.println(m.getProductCode()+" "+m.getProductCost()+""+m.getProductId());
	}
	*/ 
	

		 
		
		/*                            @QUERY                                             */
		
		/*@Query now its standard way of implementation and it is in the form of insstensive
		 * it dont support the *
		 * the word "select" should be in the form of capital (or) small whatever
		 * the word "from" should be in the form of capital (or) small whatever
		 * it wont support the "*" */
		//repo.getProductData("use","EFG").forEach(System.out::println);
		
		         /*         ANOTHER WAY OF PRINTING THE DATA FROM @QUERY   */
		  /*                
			List<Product> productData = repo.getProductData("use","EFG");
        	System.out.println(productData);
         	productData.forEach(System.out::println);
		*/
	
		*/
		/*This is the way to represent the query NATIVEQUREY WHICH IS NOTHING BUT PURE SQL
		 * we need keep comment @Component*/
		//repo.getProductData("use","EFG").forEach(System.out::println);
       //List<Product>  ProductData=repo.getProductData("use","EFG");
       //System.out.println("ProductData"); 
       //ProductData.forEach(System.out::println);
       */
		
		
		/*single columns
         *multiple columns
         *all columns 
         *USING @QUERY */
	
		/*ALL COLUMNS
		repo.getAllProductInfo().forEach(System.out::println);
	   */

		/*FEW COLUMNS (ACTUAL OUTPUT IT WILL SHOWS UNREADABLE FORMAT
		 * FOR READABLE FORMAT WE NEED TO WRITE {streams} java 8
		repo.getAllProductVendorAndProductCode("ABC", "Use")
    	.stream()
    	.map(ob->ob[0]+"-"+ob[1])
        .forEach(System.out::println);
	    */	
		
		/* ONE COLUMN
	   repo.getAllProductVendor("ABC").forEach(System.out::println);
	   */
		
		/*Here iam using the like operator which is between "b" and productVendor not equals what i given
		 * 
		repo.getMyInfo("%B%","use").forEach(System.out::println);
		*/
		
		/*Here using @Query we need to get the few columns
		 * EXAMPLE
		 * product cost some value to another particular value along with the productCode details also
		 * Here i used for few Columns <Object[]>
		repo.getMyInfoB(100.0, 200.0)
		.stream()
		.map(ob->ob[0]+"-"+ob[1])
		.forEach(System.out::println);
		*/
		
		/*Here iam using not in Query for the products which are not in what i given list here below
		repo.getInfoc(Arrays.asList(100.0,100.0,100.0,100.0)).forEach(System.out::println);
		*/
		
		/* Non select Query operations
		int count=repo.removeData(200.0);
		System.out.println(count);
		*/
		/* update non select operation operation
		int count=repo.updateData(300.0);
		System.out.println(count); */
	}
	}
		
	