/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adding;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Kishan
 */
public class Selection
    {

        private String tag1;
        private String path1;
        private String action1;


        public Selection( String tag1, String path1, String action )
        {
            this.tag1 =tag1;
            this.path1 = path1;
            this.action1=action;
        }

        /**
         * @return the tag
         */
        public String getTag1() {
            return tag1;
        }

        /**
         * @param tag the tag to set
         */
        public void setTag1(String tag) {
            this.tag1 = tag;
        }

         /**
         * @return the tag
         */
        public String getAction1() {
            return action1;
        }

        /**
         * @param tag the tag to set
         */
        public void setAction1(String action) {
            this.action1 = "delete";
        }
        /**
         * @return the path
         */
        public String getPath1() {
            return path1;
        }

        /**
         * @param path the path to set
         */
        public void setPath1(String path) {
            this.path1 = path;
        }



    }

