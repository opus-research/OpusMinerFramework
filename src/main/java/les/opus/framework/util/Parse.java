///*
// * Copyright 2018 Daniel.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package les.opus.framework.util;
//
//
//import com.google.common.reflect.TypeToken;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import framework.util.RuntimeTypeAdapterFactory;
//import les.opus.framework.tool.PersistenceTool;
//import les.opus.framework.tool.Tool;
//import les.opus.framework.tool.ToolCommand;
//import java.lang.reflect.Type;
//import java.util.List;
//
///**
// *
// * @author daniel
// * @param <E>
// */
//public class Parse<E> {
//
//    private static Gson gson;
//    private RuntimeTypeAdapterFactory<Tool> adapterFactory;
//    private RuntimeTypeAdapterFactory<ToolCommand> adapterFactory2;
//
//    /**
//     *
//     */
//    public Parse() {
//        adapterFactory = RuntimeTypeAdapterFactory.of(Tool.class, "type");
//        adapterFactory2 = RuntimeTypeAdapterFactory.of(ToolCommand.class, "type2");
//
//    }
//
//    /**
//     *
//     * @param tool
//     */
//    public void addSubType(PersistenceTool tool) {
//        adapterFactory.registerSubtype(tool.getTool().getClass(), tool.getTool().getClass().getName());
//        for (ToolCommand tc : tool.getTool().getCommands()) {
//            adapterFactory2.registerSubtype(tc.getClass(), tc.getClass().getName());
//        }
//    }
//
//    /**
//     *
//     * @param obj
//     * @return
//     */
//    public String toJson(Object obj) {
//        gson = new GsonBuilder().registerTypeAdapterFactory(adapterFactory)
//                .registerTypeAdapterFactory(adapterFactory2)
//                .create();
//        return gson.toJson(obj);
//    }
//
//    /**
//     *
//     * @param json
//     * @return
//     */
//    public Object parseJsonToClass(String json) {
//        gson = new GsonBuilder().registerTypeAdapterFactory(adapterFactory)
//                .registerTypeAdapterFactory(adapterFactory2)
//                .create();
//        Type typeList = new TypeToken<List<PersistenceTool>>() {
//        }.getType();
//
//        return gson.fromJson(json, typeList);
//
//    }
//
//}
