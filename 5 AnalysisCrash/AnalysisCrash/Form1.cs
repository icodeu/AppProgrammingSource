using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Data.SqlClient;
using System.Text.RegularExpressions;
using System.Xml;

namespace AnalysisCrash
{

    public partial class Form1 : Form
    {
        Dictionary<string, string> dicCrash = new Dictionary<string, string>();

        Dictionary<string, RegexRule> dicRegexRule = new Dictionary<string, RegexRule>();

        public Form1()
        {
            InitializeComponent();
            FillGridView();
        }

        void FillGridView()
        {
            dgRules.Rows.Clear();
            
            XmlDocument xml = new XmlDocument();
            xml.Load("RegexRules.xml");
            XmlElement node = xml.DocumentElement;

            dgRules.Rows.Add(node.ChildNodes.Count - 1);

            int i = 0;
            foreach (XmlNode item in node.ChildNodes)
            {
                dicRegexRule[item.Attributes["name"].InnerText] = new RegexRule(item.Attributes["from"].InnerText, item.Attributes["to"].InnerText);

                dgRules.Rows[i].Cells[0].Value = item.Attributes["from"].InnerText;
                dgRules.Rows[i].Cells[1].Value = item.Attributes["to"].InnerText;

                i++;
            }
        }

        String RegexReplace(String firstValue)
        {
            foreach (string key in dicRegexRule.Keys)
            {
                firstValue = Regex.Replace(firstValue, dicRegexRule[key].from, dicRegexRule[key].to);
            }

            return firstValue;
        }

        private void btnReplace_Click(object sender, EventArgs e)
        {
            string strConn = @"Data Source=.;Initial Catalog=CrashDB;Integrated Security=True";

            SqlConnection Conn = new SqlConnection(strConn);

            Conn.Open();

            SqlCommand cmd = new SqlCommand("select * from CrashDB", Conn);


            SqlDataReader read = cmd.ExecuteReader();

            while (read.Read())
            {
                string dis_info = (String)read["exception_stack"];
                dicCrash[(String)(read["id"].ToString())] = RegexReplace(dis_info); 
            }

            read.Close();


            foreach (var key in dicCrash.Keys)
            {
                string info = dicCrash[key].Replace("'", "''");

                String sql = "Update CrashDB Set dis_info ='" + info + "' where id = " + key;
                SqlCommand cmd2 = new SqlCommand(sql, Conn);
                cmd2.ExecuteNonQuery();
            }

            Conn.Close();

            MessageBox.Show("替换完成");
        }

    }

    public class RegexRule
    {
        public RegexRule(string from, string to)
        {

            this.from = from;
            this.to = to;
        }

        public string from;
        public string to;
    }

}