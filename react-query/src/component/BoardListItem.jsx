import React from 'react'
import useBoardStore from '../store/useBoardStore';

const BoardListItem = ({data}) => {

  let { setSelectBoardInfo } = useBoardStore();

  const boardDetailFunc = () => {
    setSelectBoardInfo(data);
  };
 
  return (
    <tr>
      <td>{data.idx}</td>
      <td>{data.title}</td>
      <td>{data.content}</td>
      <td>{data.createWriter}</td>
      <td>{data.createDt}</td>
      <td>{data.updateWriter}</td>
      <td>{data.updateDt}</td>
      <td><button onClick={boardDetailFunc}>상세</button></td>
    </tr>
  )
}

export default BoardListItem