package tabComponents;

public class musicXMLScript {
    public static void main(String[] args) {
        musicXMLScript aa = new musicXMLScript();
        System.out.println(aa.makeScript());
        //It's very draft version representing only 2 notes in 2 measures.
        //I tried to make it to understand how the tablauture is handled in musicxml format.
        //If we store this texts as musicxml file and run in music software supporting musicxml format,
        //we can get very simple tablauture score.
        //I'll send screen shot of result of this scripts in Discord.
    }

    public String makeScript() {
        String songTitle = "Title Name";

        String result =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                        + "<!DOCTYPE score-partwise PUBLIC \"-//Recordare//DTD MusicXML 3.1 Partwise//EN\" \"http://www.musicxml.org/dtds/partwise.dtd\">\n"
                        + "<score-partwise version=\"3.1\">"
                        +"  <work>\n"
                        //I think this basic information about musicXML file can be fixed.

                        + "    <work-title>" + songTitle + "</work-title>\n"
                        + "    </work>\n"// we can ask customer to put song title or not.
                        + makePartlist()
                        + makePart()
                        + "  </score-partwise>\n";
        return result;
    }

    private String makePartlist(){
        String partName = "Guitar";
        //If the tablature is for drum, it's drum

        String instrument = "Acoustic Guitar";
        //We can choose what kind of instruments is.

        String result =
                "  <part-list>\n" +
                        "    <score-part id=\"P1\">\n" +
                        //I think we need only P1 because we deal with only one instrument at once.

                        "      <part-name>" + partName + "</part-name>\n" +
                        "      <score-instrument id=\"P1-I1\">\n" +
                        "        <instrument-name>" + instrument + "</instrument-name>\n" +
                        //if this instrument-name is changed, for example from Acoustic Guital to Electric Guitar,
                        //the sound in program is changed to electric guitar sound.


                        "        </score-instrument>\n" +

                        "      <midi-device id=\"P1-I1\" port=\"1\"></midi-device>\n" +
                        "      <midi-instrument id=\"P1-I1\">\n" +
                        "        <midi-channel>1</midi-channel>\n" +
                        "        <midi-program>1</midi-program>\n" +
                        "        <volume>80</volume>\n" +
                        "        <pan>0</pan>\n" +
                        "        </midi-instrument>\n" +
                        //I'm not sure this MIDI part.


                        "      </score-part>\n" +
                        "    </part-list>\n";
        return result;
    }

    public String makePart(){
        return "  <part id=\"P1\">\n" + makeFirstMeasure() + makeOtherMeasures() + "</part>\n";
    }

    private  String makeFirstMeasure(){
        String firstMeasure =
                makeAttributes();
        String notes = makeNotes();// needs a loop for many notes
        //The first measure needs attributes part.

        return "<measure number=\"1\">\n"
                + firstMeasure
                + notes
                +"      </measure>\n";
    }
    private String makeOtherMeasures(){
        String notes = makeNotes();// needs a loop for many notes

        return "<measure number=\"" + "2" + "\">\n"
                + notes
                + "      </measure>\n";
    }
    public String makeNotes(){
        String result =
                " <note>\n" +
                        "        <pitch>\n" +
                        "          <step>" + "E" + "</step>\n" +
                        "          <octave>" + "4" + "</octave>\n" +
                        "          </pitch>\n" +
                        //It represents key for one note.

                        "        <duration>" + "2" + "</duration>\n" +
                        //Probably, we should decide its duration
                        //based on dash numbers followed after a note and before the next note or bar.

                        "        <voice>1</voice>\n" +
                        "        <type>quarter</type>\n" +
                        //I'm not sure for these

                        "        <stem>none</stem>\n" +
                        //we don't need stem because it's tablature.
                        //https://usermanuals.musicxml.com/MusicXML/Content/EL-MusicXML-stem.htm

                        "        <notations>\n" +
                        "          <technical>\n" +
                        "            <string>" + "1" + "</string>\n" +
                        "            <fret>" + "0" + "</fret>\n" +
                        "            </technical>\n" +
                        "          </notations>\n" +
                        //We decide which string will be played based on information from the bar.

                        //e.g if this key(E in Octave 4) is from 2nd guitar string,
                        //it will be a <string>2</string> <fret>5</fret>
                        //because no fret in string 1 = 5 fret in string 2

                        "        </note>\n";
        return result;
    }


    private String makeAttributes(){
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
                "        </attributes>\n";
        return attributes;
    }
}
