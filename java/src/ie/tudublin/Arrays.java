package ie.tudublin;

import processing.core.PApplet;



public class Arrays extends PApplet
{
	String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

	float[] rainfall = {200, 260, 300, 150, 100, 50, 10, 40, 67, 160, 400, 420};

	public float map1(float a, float b, float c, float d, float e)
	{
		float r1 = c -b;
		float r2 = e - d;

		float howFar = a - b;
		return d + (howFar / r1) * r2;
	}

	void randomize()
	{
		for (int i = 0; i < rainfall.length; i++) {
			rainfall[i] = random(500);
		}
	}
	int mode = 0;

	public void keyPressed() {
		
		if (key >= '0' && key <= '9') {
			mode = key - '0';
		}
		println(mode);
		
		
	}



	public void settings()
	{
		size(500, 500);

		String[] m1 = months;
		months[0] = "Jan";
		print(m1[0]);
		for(int i = 0; i < months.length; i ++)
		{
			println("Month: " + months[i] + "\t" + rainfall[i]);
		}
		for (String s : m1) {
			println(s);
		}

		int minIndex = 0;
		for(int i= 0 ; i < rainfall.length ; i ++)
		{
			if (rainfall[i] < rainfall[minIndex])
			{
				minIndex = i;
			}
		}
		
		int maxIndex = 0;
		for(int i= 0 ; i < rainfall.length ; i ++)
		{
			if (rainfall[i] > rainfall[maxIndex])
			{
				maxIndex = i;
			}
		}

		println("The month with the minimum rainfall was " + months[minIndex] + " with " + rainfall[minIndex] + "rain");
		println("The month with the max rainfall was " + months[maxIndex] + " with " + rainfall[maxIndex] + "rain");
		
		
		float tot = 0;
		for(float f:rainfall)
		{
			tot += f;
		}

		float avg = tot / (float) rainfall.length;

		// a b-c d-e;
		println(map1(5, 0, 10, 0, 100));
		// 50

		println(map1(25, 20, 30, 200, 300));
		// 250

		println(map1(26, 25, 35, 0, 100));
		// 10 


	}

	public void setup() {
		colorMode(HSB);
		background(0);
		randomize();
		
		    
	}

	
	public void draw()
	{	
		 
		switch (mode) {
			case 1:
				colorMode(HSB, months.length); // Set color mode to HSB with a maximum value of months.length
				
				// x + y axis lines
				stroke(255,0,255);
				line(50, 50, 50, 450);
				line(50, 450, 450, 450);
			
				// labeled numbers for y-axis
				for (int i = 0; i <= 420; i += 20) {
					float yValue = map(i, 0, 420, 450, 50);
					text(i, 20, yValue);
				}
			
				// labeled months for x-axis
				for (int i = 0; i < months.length; i++) {
					float xValue = map(i, 0, months.length - 1, 50, width - 50); 
					text(months[i], xValue, 470); 
				}
				
				// Draw bars for each month
				for (int i = 0; i < months.length; i++) {
					float x = map(i, 0, months.length - 1, 50, width - 50); // Adjust the range to fit the width of the canvas
					float h = map(rainfall[i], 0, max(rainfall), 0, 400); // Adjust the range to fit the height of the canvas
					float y = 450 - h; // Calculate height per bar
					
					// Set fill color for the bar using HSB
					fill(i, months.length, months.length);
					rect(x, y, (width - 100) / months.length, h); 
				}
				break;

			case 2:
				colorMode(RGB,months.length);
				
				// x + y axis lines
				stroke(255,0,255);
				line(50, 50, 50, 450);
				line(50, 450, 450, 450);
			
				// labeled numbers for y-axis
				for (int i = 0; i <= 420; i += 20) {
					float yValue = map(i, 0, 420, 450, 50);
					text(i, 20, yValue);
				}
			
				// labeled months for x-axis
				for (int i = 0; i < months.length; i++) {
					float xValue = map(i, 0, months.length - 1, 50, width - 50); 
					text(months[i], xValue, 470); 
				}
				 // Draw lines connecting points for each month
				 stroke(255,0,0); 
				 noFill(); 
				 
				 beginShape();
				 for (int i = 0; i < months.length; i++) {
					 float x = map(i, 0, months.length - 1, 50, width - 50); // Adjust the range to fit the width of the canvas
					 float y = map(rainfall[i], 0, max(rainfall), 450, 50); // Adjust the range to fit the height of the canvas
					 vertex(x, y); //specify the vertex coordinates
				 }
				 endShape();
				 break;
				 
			case 3:
				colorMode(HSB, 360, 100, 100); 
				
				float totalRainfall = 0;
				for (float rf : rainfall) {
					totalRainfall += rf;
				}
				
				float startAngle = 0; 
				for (int i = 0; i < months.length; i++) {
					// Calculate the angle for the current segment based on its proportion of the total rainfall
					float angle = map(rainfall[i], 0, totalRainfall, 0, TWO_PI);
					
					fill(map(i, 0, months.length, 0, 360), 100, 100);
					
					// Draw segment of the pie chart
					arc(224, 184, 220, 220, startAngle, startAngle + angle, PIE);
					
					
					float labelAngle = startAngle + angle / 2;
					float labelRadius = 180; 
					float labelX = 224 + cos(labelAngle) * labelRadius;
					float labelY = 184 + sin(labelAngle) * labelRadius;
					
					
					textAlign(CENTER, CENTER);
					fill(255); 
					text(months[i], labelX, labelY);
					
					//followup angle
					startAngle += angle;
				}

			 
				 break;
		}




	}
				
}
				 
			
			
				 
				

		
		




