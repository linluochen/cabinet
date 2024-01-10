package com.tools.common.utils.graph;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title: GraphUtils
 * @Author LinLuoChen
 * @ProjectName ToolCabinet
 * @Description:
 * @Date 2023/5/31 11:19
 */
public class GraphUtils {
    /**
     * 判断某一个经纬度点是否在一组经纬度范围内
     *
     * @param ALon A点经度
     * @param ALat A点纬度
     * @param ps   范围多边形经纬度集合
     * @author Klay
     * @date 2023/2/8 18:06
     */
    public static boolean isPtInPoly(double ALon, double ALat, List<JqPoint> ps) {
        int iSum, iCount, iIndex;
        double dLon1 = 0, dLon2 = 0, dLat1 = 0, dLat2 = 0, dLon;
        if (ps.size() < 3) {
            return false;
        }
        iSum = 0;
        iCount = ps.size();
        for (iIndex = 0; iIndex < iCount; iIndex++) {
            if (iIndex == iCount - 1) {
                dLon1 = ps.get(iIndex).getX();
                dLat1 = ps.get(iIndex).getY();
                dLon2 = ps.get(0).getX();
                dLat2 = ps.get(0).getY();
            } else {
                dLon1 = ps.get(iIndex).getX();
                dLat1 = ps.get(iIndex).getY();
                dLon2 = ps.get(iIndex + 1).getX();
                dLat2 = ps.get(iIndex + 1).getY();
            }
            // 以下语句判断A点是否在边的两端点的水平平行线之间，在则可能有交点，开始判断交点是否在左射线上
            if (((ALat >= dLat1) && (ALat < dLat2)) || ((ALat >= dLat2) && (ALat < dLat1))) {
                if (Math.abs(dLat1 - dLat2) > 0) {
                    //得到 A点向左射线与边的交点的x坐标：
                    dLon = dLon1 - ((dLon1 - dLon2) * (dLat1 - ALat)) / (dLat1 - dLat2);
                    // 如果交点在A点左侧（说明是做射线与 边的交点），则射线与边的全部交点数加一：
                    if (dLon < ALon) {
                        iSum++;
                    }
                }
            }
        }
        if ((iSum % 2) != 0) {
            return true;
        }
        return false;
    }

    // 获取街道坐标
    private static List<JqPoint> readerMethod(File file) throws IOException {
        List<JqPoint> ps = new ArrayList<>();
        FileReader fileReader = new FileReader(file);
        Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
        int ch = 0;
        StringBuffer sb = new StringBuffer();
        while ((ch = reader.read()) != -1) {
            sb.append((char) ch);
        }
        fileReader.close();
        reader.close();
        JSONArray features = JSON.parseObject(sb.toString()).getJSONArray("features");
        // 0 文庙街道的
        JSONArray coordinates = JSON.parseObject(features.get(0).toString()).getJSONObject("geometry").getJSONArray("coordinates");
        JSONArray array = JSON.parseArray(coordinates.toString().trim().substring(2, coordinates.toString().length() - 2));
        for (int i = 0; i < array.size(); i++) {
            String[] split = array.get(i).toString().split(",");
            JqPoint jqPoint = new JqPoint(Double.valueOf(split[0].replace("[", "")), Double.valueOf(split[1].replace("]", "")));
            ps.add(jqPoint);
        }
        System.out.println(ps);
        return ps;
    }

    public static void main(String[] args) {
        try {
            readerMethod(new File("C:\\Users\\30447\\Desktop\\ningyang.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        List<JqPoint> ps = ne
//        JqPoint n1 = new JqPoint(116.791809,35.754042 );
//
//        JqPoint n2 = new JqPoint(116.813529,35.792777 );
//        JqPoint n3 = new JqPoint(116.83011,35.742207 );
//        JqPoint n4 = new JqPoint(116.783386,35.771421 );
//
//        JqPoint n5 = new JqPoint(116.801514,35.784271 );
//        JqPoint n6 = new JqPoint(116.815974,35.764686 );
//        JqPoint n7 = new JqPoint(116.825298,35.756578 );
//        JqPoint n8 = new JqPoint(116.7943,35.76571);
//
//        JqPoint n9 = new JqPoint(116.87959,35.784322);
//
//        JqPoint n10 = new JqPoint(116.818485,35.751093);
//        JqPoint n11 = new JqPoint(116.813799,35.766625);
//        JqPoint n12 = new JqPoint(116.824926,35.753996);
//        JqPoint n13 = new JqPoint(116.830227,35.777959);
//        JqPoint n14 = new JqPoint(116.796925,35.752052);
//        JqPoint n15 = new JqPoint(116.830227,35.777959);
//        JqPoint n16 = new JqPoint(116.79638,35.77492);
//        JqPoint n17 = new JqPoint(116.804398,35.749388);
//        JqPoint n18 = new JqPoint(116.80571,35.763649);
//        JqPoint n19 = new JqPoint(116.803206,35.751135 );
//        JqPoint n20 = new JqPoint(116.825178,35.765623 );
//        JqPoint n21 = new JqPoint(116.80897,35.75508);
//        JqPoint n22 = new JqPoint(116.795682,35.774716);
//        JqPoint n23 = new JqPoint(116.798649,35.781901);
//        JqPoint n24 = new JqPoint(116.792072,35.779795);
//        JqPoint n25 = new JqPoint(116.80897, 35.75508);
//        JqPoint n26 = new JqPoint(116.801373, 35.78618);
//
//        JqPoint n27 = new JqPoint(116.780174,35.736511);
//
//        JqPoint n28 = new JqPoint(116.7923984, 35.7556685);
//        JqPoint n29 = new JqPoint(116.808,35.7764);
//        JqPoint n30 = new JqPoint(116.8181,35.77039);
//        JqPoint n31 = new JqPoint(116.83079,35.76485 );
//        JqPoint n32 = new JqPoint(116.826465,35.75486 );
//        JqPoint n33 = new JqPoint(116.802838,35.764411 );
//        JqPoint n34 = new JqPoint(116.80318,35.7563 );
//        JqPoint n35 = new JqPoint(116.814282,35.766779 );
//
//        JqPoint n36 = new JqPoint(116.4739, 35.1631 );
//        JqPoint n37 = new JqPoint(116.471814,35.4625 );
//
//        JqPoint n38 = new JqPoint(116.812605,35.758937 );
//        JqPoint n39 = new JqPoint(116.818758,35.75915 );
//        JqPoint n40 = new JqPoint(116.837491,35.754791 );
//        JqPoint n41 = new JqPoint(116.83378, 35.755061 );
//        JqPoint n42 = new JqPoint(116.814931,35.754129 );
//        JqPoint n43 = new JqPoint(116.813336,35.76168 );
//        JqPoint n44 = new JqPoint(116.822245,35.762695 );
//        JqPoint n45 = new JqPoint(116.822847,35.769021 );
//
//        // 判断是否在规定街道内
//        System.out.println("n1:" + isPtInPoly(n1.getX(), n1.getY(), ps));    // true
//        System.out.println("n2:" + isPtInPoly(n2.getX(), n2.getY(), ps));    // false
//        System.out.println("n3:" + isPtInPoly(n3.getX(), n3.getY(), ps));    // false
//        System.out.println("n4:" + isPtInPoly(n4.getX(), n4.getY(), ps));    // false
//        System.out.println("n5:" + isPtInPoly(n5.getX(), n5.getY(), ps));    // true
//        System.out.println("n6:" + isPtInPoly(n6.getX(), n6.getY(), ps));    // true
//        System.out.println("n7:" + isPtInPoly(n7.getX(), n7.getY(), ps));    // true
//        System.out.println("n8:" + isPtInPoly(n8.getX(), n8.getY(), ps));    // true
//        System.out.println("n9:" + isPtInPoly(n9.getX(), n9.getY(), ps));    // false
//        System.out.println("n10:" + isPtInPoly(n10.getX(), n10.getY(), ps)); // true
//        System.out.println("n11:" + isPtInPoly(n11.getX(), n11.getY(), ps)); // true
//        System.out.println("n12:" + isPtInPoly(n12.getX(), n12.getY(), ps)); // true
//        System.out.println("n13:" + isPtInPoly(n13.getX(), n13.getY(), ps)); // true
//        System.out.println("n14:" + isPtInPoly(n14.getX(), n14.getY(), ps)); // true
//        System.out.println("n15:" + isPtInPoly(n15.getX(), n15.getY(), ps)); // true
//        System.out.println("n16:" + isPtInPoly(n16.getX(), n16.getY(), ps)); // true
//        System.out.println("n17:" + isPtInPoly(n17.getX(), n17.getY(), ps)); // true
//        System.out.println("n18:" + isPtInPoly(n18.getX(), n18.getY(), ps)); // true
//        System.out.println("n19:" + isPtInPoly(n19.getX(), n19.getY(), ps)); // true
//        System.out.println("n20:" + isPtInPoly(n20.getX(), n20.getY(), ps)); // true
//        System.out.println("n21:" + isPtInPoly(n21.getX(), n21.getY(), ps)); // true
//        System.out.println("n22:" + isPtInPoly(n22.getX(), n22.getY(), ps)); // true
//        System.out.println("n23:" + isPtInPoly(n23.getX(), n23.getY(), ps)); // true
//        System.out.println("n24:" + isPtInPoly(n24.getX(), n24.getY(), ps)); // true
//        System.out.println("n25:" + isPtInPoly(n25.getX(), n25.getY(), ps)); // true
//        System.out.println("n26:" + isPtInPoly(n26.getX(), n26.getY(), ps)); // true
//        System.out.println("n27:" + isPtInPoly(n27.getX(), n27.getY(), ps)); // false
//        System.out.println("n28:" + isPtInPoly(n28.getX(), n28.getY(), ps)); // true
//        System.out.println("n29:" + isPtInPoly(n29.getX(), n29.getY(), ps)); // true
//        System.out.println("n30:" + isPtInPoly(n30.getX(), n30.getY(), ps)); // true
//        System.out.println("n31:" + isPtInPoly(n31.getX(), n31.getY(), ps)); // true
//        System.out.println("n32:" + isPtInPoly(n32.getX(), n32.getY(), ps)); // true
//        System.out.println("n33:" + isPtInPoly(n33.getX(), n33.getY(), ps)); // true
//        System.out.println("n34:" + isPtInPoly(n34.getX(), n34.getY(), ps)); // true
//        System.out.println("n35:" + isPtInPoly(n35.getX(), n35.getY(), ps)); // true
//        System.out.println("n36:" + isPtInPoly(n36.getX(), n36.getY(), ps)); // false
//        System.out.println("n37:" + isPtInPoly(n37.getX(), n37.getY(), ps)); // false
//        System.out.println("n38:" + isPtInPoly(n38.getX(), n38.getY(), ps)); // true
//        System.out.println("n39:" + isPtInPoly(n39.getX(), n39.getY(), ps)); // true
//        System.out.println("n40:" + isPtInPoly(n40.getX(), n40.getY(), ps)); // true
//        System.out.println("n41:" + isPtInPoly(n41.getX(), n41.getY(), ps)); // true
//        System.out.println("n42:" + isPtInPoly(n42.getX(), n42.getY(), ps)); // true
//        System.out.println("n43:" + isPtInPoly(n43.getX(), n43.getY(), ps)); // true
//        System.out.println("n44:" + isPtInPoly(n44.getX(), n44.getY(), ps)); // true
//        System.out.println("n45:" + isPtInPoly(n45.getX(), n45.getY(), ps)); // true
    }

}

