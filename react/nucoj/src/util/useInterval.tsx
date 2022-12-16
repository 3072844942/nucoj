import {useEffect, useRef} from "react";

/**
 * 自定义定时器
 * 只关注
 * @param callback
 * @param timeout
 */
export function useInterval(callback: any, timeout = 1000) {
    const latestCallback = useRef(() => {
    });

    useEffect(() => {
        latestCallback.current = callback;
    });

    useEffect(() => {
        const timer = setInterval(() => latestCallback.current(), timeout);
        return () => clearInterval(timer);
    }, []);
}