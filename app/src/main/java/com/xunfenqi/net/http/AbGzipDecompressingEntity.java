package com.xunfenqi.net.http;

import org.apache.http.HttpEntity;
import org.apache.http.entity.HttpEntityWrapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;



/**
 * 
* @ClassName: AbGzipDecompressingEntity 
* @Description: Http解压内容
* @author Xuebo Li
* @date 2015-8-14 下午2:38:24 
*
 */
public class AbGzipDecompressingEntity extends HttpEntityWrapper {
    
    public AbGzipDecompressingEntity(final HttpEntity entity){
        super(entity);
    }

    public InputStream getContent() throws IOException, IllegalStateException{
        InputStream wrappedin = wrappedEntity.getContent();
        return new GZIPInputStream(wrappedin);
    }

    public long getContentLength(){
        return -1;
    }
}