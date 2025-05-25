import java.io.FileInputStream;
import java.io.InputStream;

public class L0int {

    public static void main(String args[]) throws Exception{
		// Updated to read test files
		InputStream input = args.length > 0 ? new FileInputStream(args[0]) : System.in;
		Parser parser = new Parser(input);
		ASTNode exp;
		
		System.out.println("L0 interpreter PL MEIC 2024/25 (v0.0)\n");

		while (true) {
			try {
				System.out.print("# ");
				exp = parser.Start();
				if (exp==null) System.exit(0);
				IValue v = exp.eval(new Environment<IValue>());
				System.out.println(v.toStr());
			} catch (ParseException e) {
				System.out.println("Syntax Error.");
				parser.ReInit(System.in);

			} catch (Exception e) {
				e.printStackTrace();
				parser.ReInit(System.in);
			}
		}
    }
    
}
