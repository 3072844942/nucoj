import React  from 'react';
import MdEditor from 'md-editor-rt';

import axios from "axios";

/**
 * 文本编辑器
 * 存在问题， 如果是只修改对象中的一个属性， 则对象的所以属性都会被清空
 * @param props
 * @constructor
 */
function Editor(props: {
    editorId: string,
    text: string,
    setText: (v:string) => void,
    style?: object
}) {
    const onUploadImg = async (files: any, callback: any) => {
        const res = await Promise.all(
            files.map((file:any) => {
                return new Promise((rev, rej) => {
                    const form = new FormData();
                    form.append('file', file);

                    axios
                        .post('/api/img/upload', form)
                        .then((res) => rev(res.data.data))
                        .catch((error) => rej(error));
                });
            })
        );

        callback(res);
    }

    return (
        <MdEditor
            editorId={props.editorId}
            style={props.style}
            modelValue={props.text}
            onChange={props.setText}
            onUploadImg={onUploadImg}
        />
    );
}

export default Editor;