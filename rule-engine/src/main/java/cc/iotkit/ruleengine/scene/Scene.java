package cc.iotkit.ruleengine.scene;

import cc.iotkit.ruleengine.action.Action;
import cc.iotkit.ruleengine.filter.Filter;
import cc.iotkit.ruleengine.listener.Listener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scene {

    private String id;

    private String name;

    private List<Listener<?>> listeners;

    private List<Filter<?>> filters;

    private List<Action<?>> actions;

}
