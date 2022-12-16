import React from 'react';
import SearchIcon from "../../assets/icon/SearchIcon";
import Button from "../button/Button";

/**
 * 搜索框
 * @param props
 * @constructor
 */
function Search(props:{value:string, tags?:string[], setValue: Function, setType?: Function}) {
    const getTag = (item:string) => {
        return <Button
            context={item}
            color={
                item.match(/^[1~9]/) ? 'red' : 'green'
            }
            fontColor={'white'}
            size={3}
            enter={false}
        />
    }

    return (
        <div>
            <div style={{
                width: '100%',
                height: '7vh',
                borderRadius: '50px',
                boxShadow: '0 2px 2px 2px rgb(33,33,33, .1)',
                display: 'flex',
                alignItems: 'center'
            }}>
                <input style={{
                    width: '90%',
                    height: '100%',
                    backgroundColor: '#fff',
                    borderRadius: '50px',
                    borderColor: '#89C3EB',
                    fontSize: '26px',
                    marginRight: '3vh',
                    paddingLeft: '3vh',
                    paddingRight: '3vh',
                }} value={props.value} onChange={(evt) => {
                    props.setValue(evt.target.value)
                }}/>
                <SearchIcon></SearchIcon>
            </div>
            <div style={{
                height: '40px',
                display: 'flex',
                padding: '10px 10px 0 10px'
            }}>
                {
                    props.tags !== undefined && props.tags.map(item => <div
                        key={item}
                        style={{marginRight: '5px'}}
                        onClick={() => props.setType !== undefined && props.setType(item)}
                    >
                        {getTag(item)}
                    </div>)
                }
            </div>
        </div>
    );
}

export default Search;