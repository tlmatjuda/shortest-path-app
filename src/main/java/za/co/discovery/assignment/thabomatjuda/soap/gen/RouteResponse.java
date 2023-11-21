//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.11.21 at 10:14:36 AM SAST 
//


package za.co.discovery.assignment.thabomatjuda.soap.gen;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="totaldistance" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="hops" type="{http://gen.soap.thabomatjuda.assignment.discovery.co.za}TripHop" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "totaldistance",
    "hops"
})
@XmlRootElement(name = "RouteResponse")
public class RouteResponse {

    protected double totaldistance;
    @XmlElement(required = true)
    protected List<TripHop> hops;

    /**
     * Gets the value of the totaldistance property.
     * 
     */
    public double getTotaldistance() {
        return totaldistance;
    }

    /**
     * Sets the value of the totaldistance property.
     * 
     */
    public void setTotaldistance(double value) {
        this.totaldistance = value;
    }

    /**
     * Gets the value of the hops property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hops property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHops().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TripHop }
     * 
     * 
     */
    public List<TripHop> getHops() {
        if (hops == null) {
            hops = new ArrayList<TripHop>();
        }
        return this.hops;
    }

}
