
/**
 * 
 */
package com.npst.upi.portal.merchant.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.npst.upi.portal.merchant.dto.QRCodeRequest;
import com.npst.upi.portal.merchant.entity.Merchants;
import com.npst.upi.portal.merchant.utility.Constants;

/**
 * @author Rahul Chaudhary
 *
 */
@Component
public class BharatQrGenerator {
	private static final Logger log = LoggerFactory.getLogger(UpiQrCodeGenerator.class);
	
    private String flag;
    private String qrName,qrNamePdf;
    private String payeeVpa,payeeName;

    private String staticAmount=null,staticRemark=null;
    private String finelUrltoqr="";

    private StringBuilder tag1,tag2,tag3,tag4,tag5,tag6,tag7,tag8,tag9,tag10,tag11,tag12,tag13,tag14,tag15,tag16,urltoqr;

    private String rupayId,vpa,minAmt,txnId,url,aadharNo,marchantName,city,pin,purpose,crc,sub2_value=null;

    private String subTag1,subTag2,subTag3,subTag4,subTag5,subTag6,subTag7,subTag8,subTag9,subTag10,subTag11,subTag12,subTag13,subTag14,subTagCount15,subTag16;
    private String sub1,sub2,sub3,sub4,sub8,sub9,sub10,sub11;

    // SUB TAG 5
    private String tag5_sub5_tag1,tag5_sub5_tag2,tag5_sub5_tag3;
    private String tag5_sub5_tag1_count_rupayId,tag5_sub5_tag2_count_vpa,tag5_sub5_tag3_count_minAmt;


    //SUB TAG 6
    private String tag6_sub6_tag1,tag6_sub6_tag2,tag6_sub6_tag3;
    private String tag6_sub6_tag1_count_rupayId,tag6_sub6_tag2_count_axnId,tag6_sub6_tag3_count_url;

    //SUB TAG 7
    private String tag7_sub7_tag1,tag7_sub7_tag2;
    private String tag7_sub7_tag1_count_rupayId,tag7_sub7_tag2_count_aadhaarNo;

    //SUB TAG 15
    private String tag15_sub15_tag1;
    private String tag15_sub15_tag1_count_purpose;
    private String sub_subTagCount15;
    
    @Autowired
    private QrGeneratorUtil qrutil;
    
    private String qrstringDefination(Merchants merchant) {

        subTag1="02";
        sub1="01";

        tag1=new StringBuilder().append( Constants.tag1).append(subTag1).append(sub1);

        subTag2="02";
        sub2=sub2_value; // TODO value set static 11 / dynamic 12

        tag2=new StringBuilder().append(Constants.tag2).append(subTag2).append(sub2);


        subTag3="16";
        sub3="1591640000000011";  // (STR0000000148172)

        tag3=new StringBuilder().append(Constants.tag3).append(subTag3).append(sub3);

        subTag4="26"; // TODO count values of acc & ifsc
        sub4="ABCD0000000010204201300887";

        tag4=new StringBuilder().append(Constants.tag4).append(subTag4).append(sub4);

        subTag5="42"; // TODO count rupay ID / vpa / minn amt
        tag5_sub5_tag1="00";
        tag5_sub5_tag2="01";
        tag5_sub5_tag3="02";


        vpa=payeeVpa=merchant.getMerchantVPA();
        String vpaLength= String.valueOf(vpa.length());

        tag5_sub5_tag1_count_rupayId="10"; //TODO Count values rupay_id val1 / VPA val2 / Min amt val3
        tag5_sub5_tag2_count_vpa=vpaLength;
        tag5_sub5_tag3_count_minAmt="03";

        rupayId="A000000524";
       // vpa="9082573640@abcdef";
        minAmt="1.0"; // TODO value qr is dynamic then min amt is 0.0

        tag5=new StringBuilder().append(Constants.tag5).append(subTag5).append(tag5_sub5_tag1).append(tag5_sub5_tag1_count_rupayId).append(rupayId+tag5_sub5_tag2).append(tag5_sub5_tag2_count_vpa).append(vpa+tag5_sub5_tag3).append(tag5_sub5_tag3_count_minAmt).append(minAmt);

        subTag6="48"; // TODO count rupay ID / txn Id / url
        tag6_sub6_tag1="00";
        tag6_sub6_tag2="01";
        tag6_sub6_tag3="02";

        tag6_sub6_tag1_count_rupayId="10"; //TODO counting values to set rupayId/txnId/Url
        tag6_sub6_tag2_count_axnId="30";
        tag6_sub6_tag3_count_url="26";

        rupayId="A000000524";
        txnId="ABC15A5E7BFD605483BBC740065099";
        url="https://www.abcdefbank.com";

        tag6=new StringBuilder().append(Constants.tag6).append(subTag6).append(tag6_sub6_tag1).append(tag6_sub6_tag1_count_rupayId).append(rupayId).append(tag6_sub6_tag2).append(tag6_sub6_tag2_count_axnId).append(txnId).append(tag6_sub6_tag3).append(tag6_sub6_tag3_count_url).append(url);

        subTag7="30"; // TODO rupayId / Aadhaar no
        tag7_sub7_tag1="00";
        tag7_sub7_tag2="01";

        tag7_sub7_tag1_count_rupayId="10";  //TODO counting values to set rupayId / aadhaar no
        tag7_sub7_tag2_count_aadhaarNo="12";  // aadhaar no

        rupayId="A000000524";
        aadharNo=merchant.getAadharNumber();

        tag7=new StringBuilder().append(Constants.tag7).append(subTag7).append(tag7_sub7_tag1).append(tag7_sub7_tag1_count_rupayId).append(rupayId).append(tag7_sub7_tag2).append(tag7_sub7_tag2_count_aadhaarNo).append(aadharNo);

        subTag8="04";
        sub8="7372";

        tag8=new StringBuilder().append(Constants.tag8).append(subTag8).append(sub8);

        subTag9="03";
        sub9="INR";

        tag9=new StringBuilder().append(Constants.tag9).append(subTag9).append(sub9);

        subTag10="04"; // TODO static qr Amount counting with decimal . two values count amout length and set
        sub10="0.00"; // max amount

        if ("static".equalsIgnoreCase(flag)){
            String amt= String.valueOf(staticAmount.length());

            if (amt.length()>1){
                subTag10=amt;
            } else {
                subTag10="0"+amt;
            }
            sub10=staticAmount;
        }

        tag10=new StringBuilder().append(Constants.tag10).append(subTag10).append(sub10);

        subTag11="02"; // hardcode counter code
        sub11="IN";

        tag11=new StringBuilder().append(Constants.tag11).append(subTag11).append(sub11);

        subTag12="09"; // TODO count value and set marchant name
        marchantName=payeeName; // marchant holder name harcode string

        tag12=new StringBuilder().append(Constants.tag12).append(subTag12).append(marchantName);

        subTag13="06"; // TODO couunt city value and set
        city="PANVEL"; //  marchant city address

        tag13=new StringBuilder().append(Constants.tag13).append(subTag13).append(city);

        subTag14="06"; // TODO pin code
        pin="410206";

        tag14=new StringBuilder().append(Constants.tag14).append(subTag14).append(pin);

        subTagCount15="08"; // todo count all valus and set for remark
        tag15_sub15_tag1="08"; // purpose id hardcode constant
        tag15_sub15_tag1_count_purpose="04"; // count purpose and set valus
        purpose="test"; // harcode string


        if ("static".equalsIgnoreCase(flag)){
            String remk= String.valueOf(staticRemark.length());

            if (remk.length()>1){
                tag15_sub15_tag1_count_purpose=remk;
            } else {
                tag15_sub15_tag1_count_purpose="0"+remk;
            }
            purpose=staticRemark;

            String tag15_sub15_tag1_count=tag15_sub15_tag1_count_purpose;
            String count= String.valueOf(tag15_sub15_tag1_count.length());
            if (count.length()>1){
                tag15_sub15_tag1=count;
            } else {
                tag15_sub15_tag1="0"+count;
            }

            int val1=Integer.parseInt(tag15_sub15_tag1_count_purpose);

            int subCount=val1+2;

            String finelCount=String.valueOf(subCount);
            if (finelCount.length()>1){
                subTagCount15=finelCount; //todo remove after ttesting (tag15_sub15_tag1)
            } else {
                subTagCount15="0"+finelCount; //todo remove after ttesting (tag15_sub15_tag1)
            }
        }

        tag15=new StringBuilder().append(Constants.tag15).append(subTagCount15).append(tag15_sub15_tag1).append(tag15_sub15_tag1_count_purpose).append(purpose);

        subTag16="04"; // count and set values crc
        crc="fd4b";

        tag16=new StringBuilder().append(Constants.tag16).append(subTag16).append(crc);

        urltoqr=new StringBuilder().append(tag1).append(tag2).append(tag3).append(tag4).append(tag5).append(tag6).append(tag7).append(tag8).append(tag9).append(tag10).append(tag11).append(tag12).append(tag13).append(tag14).append(tag15).append(tag16);

        return urltoqr.toString();

      //  urltoqr="000201010212061615916400000000110826ABCD000000001020420138888826420010A00000052401179082573640@abcdef02030.027480010A0000005240130COB15A5E7BFD605483BBC7400650990226https://www.cosmosbank.com28300010A0000005240112123456789123520473725303INR54040.005802IN5909CAKE SHOP6006PANVEL610641020662080804test6304fd4b";
    }

	public void generateQr(Merchants merchant) {
		String filePath;
		try {
			filePath = qrutil.generateFilePath(merchant);
			log.info("File Path is{}",filePath);
			String fileName = qrutil.generateFileName(merchant);
			log.info("File Name is{}",fileName);
			qrutil.generateQr(merchant, qrstringDefination(merchant), filePath, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
