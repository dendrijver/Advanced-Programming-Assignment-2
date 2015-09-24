package Assignment2;
/** ADT for the class Identifier.
*
* @author Ben den Drijver and Rink Stiekema
*
* @elements
*    characters of the type char
* @structure
*    linear
* @domain
*       all rows of alphanumeric characters with a length of at least one character, starting with a letter
* @constructor
*    Identifier(String s);
*        <dl>
*        <dt><b>PRE-condition</b><dd>String s consists of alphanumeric characters, is non-empty, and starts with a letter.
*        <dt><b>POST-condition</b><dd>An Identifier object containing String s exists.
*        </dl>
*    <br>
*    Identifier(Identifier src);
*        <dl>
*        <dt><b>PRE-condition</b><dd>-
*        <dt><b>POST-condition</b><dd>The new
*        Identifier-object is a copy of the src.
*        </dl>
**/

public interface Identifier {
    
    /** PRE - String s of alphanumeric characters is non-empty and starts with a letter
       POST - The object contains String s  
    */
    public void init(String s);
    
    /** PRE -
       POST - returns true: if identifier is equal to the parameter
          returns false: if identifier is not equal to the parameter
    */
    public boolean isEqualTo(Identifier identifier);
        
    /**PRE -
      POST - returns a String representation of the object
    */
    public String toString();
}
