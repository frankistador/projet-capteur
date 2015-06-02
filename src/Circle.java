import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.Timer;




public class Circle implements Shape {
	private double x, y, radius, radiusOrigin;
	private Timer timer ;
	private boolean wave;

	
	public Circle(double x, double y, double radius, boolean wave)
	{
	    this.x = x;
	    this.y = y;
	    this.radius = radius;
	    this.radiusOrigin = radius;
	    this.wave = wave;
	    this.timer = this.createTimer();
	    this.timer.start();
	}

	
	//Clignote et avancement du cercle
	private Timer createTimer() {
		
		ActionListener action = new ActionListener() {
			
			//Méthode pour chaque tic de timer
			public void actionPerformed (ActionEvent event)
			{
				waving();
			}

		};

		return new Timer(500, action);
	}
//	
//	private void move() {
//		
//		this.x = this.x +10;
//		this.y = this.y +10;
//	}
	
	private void waving() {
		
//		System.out.println("Wave"+wave);
//		System.out.println("this"+this.wave);
		if(this.wave == true) {

			if(radius*1.2 >= radiusOrigin)
			{
				this.radius = this.radius/10;
			}
			else
			{
				this.radius = this.radius + this.radiusOrigin/10;
			}
		}

		
	}
	
	// Tests if the specified coordinates are inside the boundary of the
	// Shape
	public boolean contains(double x, double y) {
		return this.getRadius() / 2 >= Math.sqrt((Math.pow((x - this.x), 2) + (Math.pow((y - this.y),2))));

	}

	// Tests if the interior of the Shape entirely contains the specified
	// rectangular area
	public boolean contains(double x, double y, double w, double h) {
		if (this.contains(x, y) && this.contains(x + w, y) && this.contains(x + w, y + h) && this.contains(x, y + h)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean contains(Point2D p) {
		if (this.contains(p.getX(), p.getY())) {
			return true;
		} else {
			return false;
		}
	}

	public void draw(Graphics2D g) {
		g.drawOval((int) x - (int) radius / 2, (int) y - (int) radius / 2,(int) radius, (int) radius);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle2D getBounds2D() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean intersects(double x, double y, double w, double h) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean intersects(Rectangle2D r) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Rectangle2D r) {
		return this.contains(r.getX(), r.getY(), r.getWidth(),
				r.getHeight());

	}

	@Override
	public PathIterator getPathIterator(AffineTransform at) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at, double flatness) {
		// TODO Auto-generated method stub
		return null;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

}