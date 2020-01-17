package org.apache.rocketmq.example.filter;

import org.apache.rocketmq.common.filter.FilterContext;
import org.apache.rocketmq.common.filter.MessageFilter;
import org.apache.rocketmq.common.message.MessageExt;

public class MessageFilterImpl implements MessageFilter {

    @Override
    public boolean match(MessageExt msg, FilterContext context) {
        String property = msg.getProperty("SequenceId");
        if (property != null) {
            int id = Integer.parseInt(property);
            if (((id % 10) == 0) &&
                (id > 100)) {
                return true;
            }
        }

        return true;
    }
}
