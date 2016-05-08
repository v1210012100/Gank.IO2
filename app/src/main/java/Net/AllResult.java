package Net;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 18855127160 on 2016/5/2.
 */
public class AllResult {
        private boolean error;

        private List<Results> results ;

        public void setError(boolean error){
            this.error = error;
        }
        public boolean getError(){
            return this.error;
        }
        public void setResults(List<Results> results){
            this.results = results;
        }
        public List<Results> getResults(){
            return this.results;
        }
    public static class Results implements Serializable {
        private String _id;

        private String createdAt;

        private String desc;

        private String publishedAt;

        private String source;

        private String type;

        private String url;

        private boolean used;

        private String who;

        public void set_id(String _id){
            this._id = _id;
        }
        public String get_id(){
            return this._id;
        }
        public void setCreatedAt(String createdAt){
            this.createdAt = createdAt;
        }
        public String getCreatedAt(){
            return this.createdAt;
        }
        public void setDesc(String desc){
            this.desc = desc;
        }
        public String getDesc(){
            return this.desc;
        }
        public void setPublishedAt(String publishedAt){
            this.publishedAt = publishedAt;
        }
        public String getPublishedAt(){
            return this.publishedAt;
        }
        public void setSource(String source){
            this.source = source;
        }
        public String getSource(){
            return this.source;
        }
        public void setType(String type){
            this.type = type;
        }
        public String getType(){
            return this.type;
        }
        public void setUrl(String url){
            this.url = url;
        }
        public String getUrl(){
            return this.url;
        }
        public void setUsed(boolean used){
            this.used = used;
        }
        public boolean getUsed(){
            return this.used;
        }
        public void setWho(String who){
            this.who = who;
        }
        public String getWho(){
            return this.who;
        }

    }
}

