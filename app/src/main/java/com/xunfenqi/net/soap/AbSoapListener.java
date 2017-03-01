package com.xunfenqi.net.soap;

import android.os.Handler;
import android.os.Message;

/**
 * @author Xuebo Li
 * @ClassName: AbSoapListener
 * @Description: Soap执行监听器
 * @date 2015-8-14 下午2:47:40
 */
public abstract class AbSoapListener {


    /**
     * The m handler.
     */
    private Handler mHandler;

    /**
     * 成功.
     */
    protected static final int SUCCESS_MESSAGE = 0;

    /**
     * 失败.
     */
    protected static final int FAILURE_MESSAGE = 1;

    /**
     * 开始.
     */
    protected static final int START_MESSAGE = 4;

    /**
     * 完成.
     */
    protected static final int FINISH_MESSAGE = 5;

    /**
     * 进行中.
     */
    protected static final int PROGRESS_MESSAGE = 6;


    /**
     * Instantiates a new ab soap listener.
     */
    public AbSoapListener() {
        mHandler = new SoapResponderHandler(this);
    }

    /**
     * 描述：获取数据成功会调用这里.
     *
     * @param statusCode the status code
     * @param content    the content
     */
    public abstract void onSuccess(int statusCode, String content);

    /**
     * 描述：失败，调用.
     *
     * @param statusCode the status code
     * @param content    the content
     * @param error      the error
     */
    public abstract void onFailure(int statusCode, String content, Throwable error);


    /**
     * 描述：获取数据开始.
     */
    public void onStart() {
    }

    ;


    /**
     * 完成后调用，失败，成功，调用.
     */
    public void onFinish() {
    }

    ;

    /**
     * 开始消息.
     */
    public void sendStartMessage() {
        sendMessage(obtainMessage(START_MESSAGE, null));
    }

    /**
     * 完成消息.
     */
    public void sendFinishMessage() {
        sendMessage(obtainMessage(FINISH_MESSAGE, null));
    }

    /**
     * 失败消息.
     *
     * @param statusCode the status code
     * @param content    the content
     * @param error      the error
     */
    public void sendFailureMessage(int statusCode, String content,
                                   Throwable error) {
        sendMessage(obtainMessage(FAILURE_MESSAGE, new Object[]{statusCode,
                content, error}));
    }

    /**
     * Send success message.
     *
     * @param statusCode the status code
     * @param content    the content
     */
    public void sendSuccessMessage(int statusCode, String content) {
        sendMessage(obtainMessage(SUCCESS_MESSAGE, new Object[]{statusCode,
                content}));
    }

    /**
     * 发送消息.
     *
     * @param msg the msg
     */
    public void sendMessage(Message msg) {
        if (msg != null) {
            msg.sendToTarget();
        }
    }

    /**
     * 构造Message.
     *
     * @param responseMessage the response message
     * @param response        the response
     * @return the message
     */
    protected Message obtainMessage(int responseMessage, Object response) {
        Message msg;
        if (mHandler != null) {
            msg = mHandler.obtainMessage(responseMessage, response);
        } else {
            msg = Message.obtain();
            if (msg != null) {
                msg.what = responseMessage;
                msg.obj = response;
            }
        }
        return msg;
    }


    /**
     * The Class SoapResponderHandler.
     */
    private class SoapResponderHandler extends Handler {

        /**
         * 响应数据.
         */
        private Object[] content;

        /**
         * 响应消息监听.
         */
        private AbSoapListener listener;

        /**
         * 响应消息处理.
         *
         * @param listener the listener
         */
        public SoapResponderHandler(AbSoapListener listener) {
            this.listener = listener;
        }

        /* (non-Javadoc)
         * @see android.os.Handler#handleMessage(android.os.Message)
         */
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case START_MESSAGE:
                    listener.onStart();
                    break;
                case FAILURE_MESSAGE:
                    content = (Object[]) msg.obj;
                    listener.onFailure((Integer) content[0], (String) content[1], (Throwable) content[2]);
                    break;
                case SUCCESS_MESSAGE:
                    content = (Object[]) msg.obj;
                    if (content.length >= 2) {
                        listener.onSuccess((Integer) content[0], (String) content[1]);
                    }


                    break;
                case FINISH_MESSAGE:
                    listener.onFinish();
                    break;

                default:
                    break;
            }
        }
    }

}
