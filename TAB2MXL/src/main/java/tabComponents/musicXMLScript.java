package tabComponents;

public class musicXMLScript {
	
	  public String makeGuitarAttributes(){
		  
		  //I think figuring out how musicXML file make their format is the first
		  //because we don't know which information we need from the bar without this knowledge.
		  //So, this is just for attributes part for guitar Tablature, roughly.
		  
		  
		  
	        String division = "4";
	        //need information from txt file or customer directly.
	        //how many divisions per quarter note are used to indicate a note's duration.
	        //higher division = faster songs.

	        String fifths = "0";
	        //it's for key signature(positive num = sharps, negative num = flats)
	        //But, it doesn't need for guitar Tablature. So, I set it 0.

	        String beats = "4";
	        //numerator of the time signature
	        String beat_type = "4";
	        //denominator of a time signature
	        //need information from txt file or customer directly.

	        String sign = "TAB";
	        //Tablature format in musicXML.

	        String line1_tuningStep = "E";
	        String line1_tuningOctave = "2";
	        String line2_tuningStep = "A";
	        String line2_tuningOctave = "2";
	        String line3_tuningStep = "D";
	        String line3_tuningOctave = "3";
	        String line4_tuningStep = "G";
	        String line4_tuningOctave = "3";
	        String line5_tuningStep = "B";
	        String line5_tuningOctave = "3";
	        String line6_tuningStep = "E";
	        String line6_tuningOctave = "4";
	        //If customer want tuning for their Tablature, (e.g Tuning D-A-D-G-B-D, Capo 4th fret),
	        //we need more information from txt file or customer directly.
	        //If not, we can use default guitar tuning like this.

	        String capoNumber = "4";
	        //for capo

	        String attributes = " <attributes>\n" +
	                "        <divisions>"+ division + "</divisions>\n" +
	                "        <key>\n" +
	                "          <fifths>"+ fifths +"</fifths>\n" +
	                "          </key>\n" +
	                "        <time>\n" +
	                "          <beats>" + beats +"</beats>\n" +
	                "          <beat-type>" + beat_type + "</beat-type>\n" +
	                "          </time>\n" +
	                "        <clef>\n" +
	                "          <sign>" + sign +"</sign>\n" +
	                "          <line>" + 5 +"</line>\n" +
	                // Probably, this line is fixed for guitar Tablature. I'm not sure.

	                "          </clef>\n" +
	                "        <staff-details>\n" +
	                "          <staff-lines>6</staff-lines>\n" +
	                "          <staff-tuning line=\"1\">\n" +
	                "            <tuning-step>" + line1_tuningStep + "</tuning-step>\n" +
	                "            <tuning-octave>" + line1_tuningOctave + "</tuning-octave>\n" +
	                "            </staff-tuning>\n" +
	                "          <staff-tuning line=\"2\">\n" +
	                "            <tuning-step>" + line2_tuningStep + "</tuning-step>\n" +
	                "            <tuning-octave>" + line2_tuningOctave +"</tuning-octave>\n" +
	                "            </staff-tuning>\n" +
	                "          <staff-tuning line=\"3\">\n" +
	                "            <tuning-step>" + line3_tuningStep + "</tuning-step>\n" +
	                "            <tuning-octave>" + line3_tuningOctave + "</tuning-octave>\n" +
	                "            </staff-tuning>\n" +
	                "          <staff-tuning line=\"4\">\n" +
	                "            <tuning-step>" + line4_tuningStep + "</tuning-step>\n" +
	                "            <tuning-octave>" + line4_tuningOctave +"</tuning-octave>\n" +
	                "            </staff-tuning>\n" +
	                "          <staff-tuning line=\"5\">\n" +
	                "            <tuning-step>" + line5_tuningStep + "</tuning-step>\n" +
	                "            <tuning-octave>" + line5_tuningOctave +"</tuning-octave>\n" +
	                "            </staff-tuning>\n" +
	                "          <staff-tuning line=\"6\">\n" +
	                "            <tuning-step>" + line6_tuningStep +"</tuning-step>\n" +
	                "            <tuning-octave>" + line6_tuningOctave +"</tuning-octave>\n" +
	                "            </staff-tuning>\n" +
	                //"            <capo>" + capoNumber + "</capo>\n" +
	                //If customer want to use capo

	                "          </staff-details>\n" +
	                "        </attributes>";
	        return attributes;
	    }
}
