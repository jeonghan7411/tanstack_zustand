import React from 'react'
import useBoardStore from '../store/useBoardStore'
import { useBoardDelete } from '../queries/useBoardQueries';
import BoardCreate from './BoardCreate';

const BoardDetail = () => {

  const [updateForm ,setUpdateForm] = React.useState(false);

  let { selectBoardInfo ,setSelectBoardInfo} = useBoardStore();
  const { mutate: deleteBoard, isLoading, isSuccess, isError } = useBoardDelete();

  if (!selectBoardInfo.idx) return <div>선택된 게시물이 없습니다.</div>;

  const handleDelete = () => {
    deleteBoard(selectBoardInfo.idx); 
    setSelectBoardInfo({});
  };

  return (
    <div>
      <h4>여기는 상세정보 </h4>
      <div>
        <span>{selectBoardInfo.idx}</span>
        <span>{selectBoardInfo.title}</span>
        <span>{selectBoardInfo.content}</span>
        <span>{selectBoardInfo.createWriter}</span>
        <span>{selectBoardInfo.createDt}</span>
        <span>{selectBoardInfo.updateWriter}</span>
        <span>{selectBoardInfo.updateDt}</span>
        <button onClick={()=>setUpdateForm(true)}>수정</button>
        <button onClick={handleDelete}>삭제</button>
      </div>
      {isLoading && <div>게시글을 삭제하는 중...</div>}
      {isSuccess && <div>게시글이 성공적으로 삭제되었습니다!</div>}
      {isError && <div>게시글 삭제 중 오류가 발생했습니다: {isError.message}</div>}

      {updateForm && <BoardCreate data={selectBoardInfo} setUpdateForm={setUpdateForm}/>}
    </div>
  )
}

export default BoardDetail