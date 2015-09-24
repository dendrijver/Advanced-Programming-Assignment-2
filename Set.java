package Assignment2;
/** ADT for the class Set.
*
* @author Ben den Drijver and Rink Stiekema
* @elements
*    Identifiers of type Identifier
* @structure
*    none
* @domain
*    All sets containing 0 to 20 Identifiers
* @constructor
*    Set();
*        <dl>
*        <dt><b>PRE-condition</b><dd>-
*        <dt><b>POST-condition</b><dd>- A new empty Set object is created
*        </dl>
*    <br>
*    Set(Set src);
*        <dl>
*        <dt><b>PRE-condition</b><dd>-
*        <dt><b>POST-condition</b><dd>The new
*        Set-object is a copy of the src.
*        </dl>
**/

public interface Set {
    /** PRE -
       POST - the object is initialized to be an empty Set object
    */
    void init();
    
    /** PRE -
       POST - the Identifier is in the Set. Throws Exceptions if the set would contain more than 20 elements after adding Identifier.
    */
    void add(Identifier identifier) throws Exceptions;

    /** PRE -
        POST - the Identifier is not in the set
    */
    void remove(Identifier identifier);

    /** PRE -
       POST - returns true: if the set contains the Identifier.
          returns false: otherwise
    */
    boolean has(Identifier identifier);
    
    /** PRE - the set is non empty
       POST - a Identifier returned
    */
    Identifier get();
    
    /** PRE - 
        POST - the size of the set is returned
     */
    int size(); 
    
    /** PRE -
        POST - a Set object containing the union of this Set and set is returned. Throws Exceptions if after combining the two sets,  the total amount of Identifiers turns out to be larger than 20.  
    */
    Set union(Set set) throws Exceptions; 

    /** PRE -
        POST - a Set object containing the difference of this Set and set is returned  
     * @throws Exceptions 
    */
    Set difference(Set set) throws Exceptions;

    /** PRE -
        POST - a Set object containing the intersection of this Set and set is returned  
    */
    Set intersection(Set set) throws Exceptions;

    /** PRE -
        POST - a Set object containing the symmetric difference of this Set and set is returned. Throws Exceptions if after combining the two sets, the total amount of Identifiers turns out to be larger than 20.
    */
    Set symDifference(Set set) throws Exceptions;
}