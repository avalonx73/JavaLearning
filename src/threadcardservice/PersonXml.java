package threadcardservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>Java class for person complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="person"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="systemId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="rnk" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="kf" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="dateOn" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="dateOff" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="okpo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="isOkpoExclusion" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="isOkpoRefuse" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="lastChangeDt" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="relatedPersons" type="{}relatedPersonListType" minOccurs="0"/&gt;
 *         &lt;element name="products" type="{}productsListType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "person", propOrder = {
        "systemId",
        "rnk",
        "kf",
        "dateOn",
        "dateOff",
        "okpo",
        "isOkpoExclusion",
        "isOkpoRefuse",
        "lastChangeDt",
        "relatedPersons",
        "products"
})

public class PersonXml {

    @XmlElement(required = true)
    protected String systemId;
    @XmlElement(required = true)
    protected String rnk;
    protected Integer kf;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateOn;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateOff;
    protected String okpo;
    protected boolean isOkpoExclusion;
    protected boolean isOkpoRefuse;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastChangeDt;

    /**
     * Gets the value of the systemId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * Sets the value of the systemId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSystemId(String value) {
        this.systemId = value;
    }

    /**
     * Gets the value of the rnk property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRnk() {
        return rnk;
    }

    /**
     * Sets the value of the rnk property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRnk(String value) {
        this.rnk = value;
    }

    /**
     * Gets the value of the kf property.
     */
    public Integer getKf() {
        return kf;
    }

    /**
     * Sets the value of the kf property.
     *
     */
    public void setKf(Integer value) {
        this.kf = value;
    }

    /**
     * Gets the value of the dateOn property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getDateOn() {
        return dateOn;
    }

    /**
     * Sets the value of the dateOn property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setDateOn(XMLGregorianCalendar value) {
        this.dateOn = value;
    }

    /**
     * Gets the value of the dateOff property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getDateOff() {
        return dateOff;
    }

    /**
     * Sets the value of the dateOff property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setDateOff(XMLGregorianCalendar value) {
        this.dateOff = value;
    }

    /**
     * Gets the value of the okpo property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getOkpo() {
        return okpo;
    }

    /**
     * Sets the value of the okpo property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setOkpo(String value) {
        this.okpo = value;
    }

    /**
     * Gets the value of the isOkpoExclusion property.
     *
     */
    public boolean isIsOkpoExclusion() {
        return isOkpoExclusion;
    }

    /**
     * Sets the value of the isOkpoExclusion property.
     *
     */
    public void setIsOkpoExclusion(boolean value) {
        this.isOkpoExclusion = value;
    }

    /**
     * Gets the value of the isOkpoRefuse property.
     *
     */
    public boolean isIsOkpoRefuse() {
        return isOkpoRefuse;
    }

    /**
     * Sets the value of the isOkpoRefuse property.
     *
     */
    public void setIsOkpoRefuse(boolean value) {
        this.isOkpoRefuse = value;
    }

    /**
     * Gets the value of the lastChangeDt property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getLastChangeDt() {
        return lastChangeDt;
    }

    /**
     * Sets the value of the lastChangeDt property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setLastChangeDt(XMLGregorianCalendar value) {
        this.lastChangeDt = value;
    }

}
