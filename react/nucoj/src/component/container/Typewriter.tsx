import React, {useEffect, useState} from 'react';

/**
 * 打字机效果
 * @param props
 * @constructor
 */
function Typewriter(props: {
    value: string,
    style?: object,
    cursor: boolean
}) {
    const [string, setString] = useState<string>('')

    useEffect(() => {
        let len = 1
        let timer: any = undefined
        if (props.value !== undefined) {
             timer = setInterval(() => {
                console.log(props.value)
                let newString = props.value.substring(0, len)
                len++
                setString(newString)

            }, 500)
        }
        return () => {
            clearInterval(timer)
        }
    }, [props.value])
    return (
        <div style={props.style}>
            {string}
            {
                props.cursor ? <span style={{animation: 'phantom 0.8s linear infinite'}}>|</span> : null
            }
        </div>
    );
}
export default Typewriter;