import java.util.*;
import java.io.*;
import java.lang.*;
public class Simplex {
	public static void main(String[] args) throws IOException{
		Scanner inp=new Scanner(new File(args[0]));
		String input = new String();
		while (inp.hasNextLine()){
			String current_line = inp.nextLine();
			input = input + current_line +"\\n";
			
		}
		//input = input +"re ew we";

		List<String> al = new ArrayList<String>();
		Scanner in=new Scanner(new File("TOKENS.txt"));
		while (in.hasNext()){
			String line = in.next();
			al.add(line);
		}
		for (int i=0;i<al.size()/2;i++){
			if (!al.get(2*i).equals("KEYWORDS")){

		String c= al.get(2*i+1);
		input=input.replace(c,"newdelimiter"+c+"newdelimiter");

	}}
		input=input.replace("   "," ");
		input=input.replace(" ","newdelimiter");
		input=input.replace("newdelimiternewdelimiter","newdelimiter");
		 String[] sat =input.split("newdelimiter");
			int j=0;
		 	while(j<sat.length-2){
		 	for (int k=0;k<al.size()/2;k++){
		 		String h=sat[j]+sat[j+1];
		 		if (sat[j+1]!=null && h.equals(al.get(2*k+1))){
					System.out.println(al.get(2*k)+" "+h);
					j+=2;
				}
			else if (sat[j].equals(al.get(2*k+1)) && !sat[j+1].equals("=") &&!sat[j].equals(".") && !sat[j].equals("/") && !sat[j].equals("\"") && !sat[j].equals("\\")){
				System.out.printf("%s          %s\n",al.get(2*k),al.get(2*k+1));
				j+=1;
				k=0;
				}	
		}
			boolean g=(Character.isLetter(sat[j].charAt(0)) || sat[j].charAt(0)=='_' )&&  sat[j].matches("[a-zA-Z0-9_]+");
			boolean a=(Character.isLetter(sat[j+1].charAt(0)) || sat[j+1].charAt(0)=='_' )&&  sat[j+1].matches("[a-zA-Z0-9_]+");
			boolean a1=sat[j].matches("[0-9]+");
			boolean a2=sat[j+2].matches("[0-9]+") && !sat[j+3].equals(".");
			if (g){
				System.out.printf("IDENTIFIER          %s\n",sat[j]);
				j+=1;
				}
			else if(sat[j].equals(".") && a){
				System.out.printf("DOT_OPERATOR          %s\n",sat[j]);
				j+=1;
			}
			else if(!sat[j-1].equals(".") && a1 && sat[j+1].equals(".") && a2){
				System.out.printf("NUMBER          %s%s%s\n",sat[j],sat[j+1],sat[j+2]);
				j+=3;
			}
			else if(!sat[j-1].equals(".") && a1 && !sat[j+1].equals(".") ){
				System.out.println("NUMBER          "+sat[j]);
				j+=1;
			}
			else if(sat[j].equals("/") && sat[j+1].equals("*")){
					j+=2;
					System.out.printf("COMMENT          /*");
					while(!sat[j].equals("*") || !sat[j+1].equals("/")){
						System.out.print(sat[j]);
						j+=1;
					}
					System.out.print("*/");
					System.out.printf("\n");
					j+=2;

				}
				else if( sat[j].equals("/") && sat[j+1].equals("/")){
					j+=2;
					System.out.printf("COMMENT          //");
					while(!sat[j].equals("\\n")){
						System.out.printf("%s ",sat[j]);
						j+=1;
					}
					System.out.printf("\n");
					j+=1;
					}
				else if( sat[j].equals("/") && (!sat[j+1].equals("*") || !sat[j+1].equals("/"))) {
					System.out.println("FORWARD_SLASH          /");
					j+=1;
				}
			else if(sat[j].equals("\"")){
				System.out.println("DOUBLE_QUOTES          \"");
				j+=1;
				System.out.printf("STRING          ");
				while (!sat[j].equals("\"")){
					System.out.printf("%s ",sat[j]);
					j+=1;
				}
				System.out.printf("\n");
				System.out.println("DOUBLE_QUOTES          \"");
				j+=1;
			}
			else{
				System.out.println("INVALID IDENTIFIER  !!! Check");
				j+=1;
			}
		  }
	}
}