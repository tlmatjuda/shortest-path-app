//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.11.20 at 10:22:39 PM SAST 
//


package za.co.discovery.assignment.thabomatjuda.soap.gen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TripHop complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TripHop"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="index" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="tripStart" type="{http://gen.soap.thabomatjuda.assignment.discovery.co.za}TripInfo"/&gt;
 *         &lt;element name="tripEnd" type="{http://gen.soap.thabomatjuda.assignment.discovery.co.za}TripInfo"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TripHop", propOrder = {
    "index",
    "tripStart",
    "tripEnd"
})
public class TripHop {

    protected int index;
    @XmlElement(required = true)
    protected TripInfo tripStart;
    @XmlElement(required = true)
    protected TripInfo tripEnd;

    /**
     * Gets the value of the index property.
     * 
     */
    public int getIndex() {
        return index;
    }

    /**
     * Sets the value of the index property.
     * 
     */
    public void setIndex(int value) {
        this.index = value;
    }

    /**
     * Gets the value of the tripStart property.
     * 
     * @return
     *     possible object is
     *     {@link TripInfo }
     *     
     */
    public TripInfo getTripStart() {
        return tripStart;
    }

    /**
     * Sets the value of the tripStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link TripInfo }
     *     
     */
    public void setTripStart(TripInfo value) {
        this.tripStart = value;
    }

    /**
     * Gets the value of the tripEnd property.
     * 
     * @return
     *     possible object is
     *     {@link TripInfo }
     *     
     */
    public TripInfo getTripEnd() {
        return tripEnd;
    }

    /**
     * Sets the value of the tripEnd property.
     * 
     * @param value
     *     allowed object is
     *     {@link TripInfo }
     *     
     */
    public void setTripEnd(TripInfo value) {
        this.tripEnd = value;
    }

}
