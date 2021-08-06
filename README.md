# p1-ChrisJose

# Continue POS system design:
	⁃	Add and edit sales orders 
	⁃	Schedule delivery dates for sales orders
	⁃	Update invoice status of orders (Quote, Unpaid, Paid, Delivered, Invoiced)
	⁃	Handle payment information
	⁃	Add/refund/view payments on orders (refunds include partial/full refunds for returned items, low price guarantee, defective items, etc)

# Project Definitions
	⁃	Status/Invoice status of orders will follow stages:
	⁃	Quote indicates level of interest in sales order but non-final commitment
	⁃	Unpaid indicates order should count towards employee performance based on a firm commitment from guest, who has not fully paid for the order
	⁃	Paid indicates the order has been fully paid for
	⁃	Delivered indicates the order’s delivery date value has been passed
	⁃	Invoiced indicates the order has been recognized as being transferred from company to guest

# Technical Requirements
	⁃	GitHub Organization repo (SakuraMatrix)
	⁃	Java 8+
	⁃	Maven
	⁃	Junit 4+
	⁃	SLF4J: Log4J2, or Logback, etc.
	⁃	Reactor Netty (io.projectreactor.netty:reactor-netty:1.0.9)
	⁃	Apache Cassandra w/ Datastax driver
	⁃	Spring Framework
	⁃	Spring Core/Beans/Context
	⁃	Amazon Web Services (kinda optional)


