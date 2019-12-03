import React from "react";
import './SearchForm.scss';

const SearchForm = (props) => {
  return (
    <div className="search-block">
      <form>
        <div className="form-group form-group-search">
          <input
            className="form-control form-control-lg"
            type="text"
            placeholder="Type keywords here..."
            value={props.value}
            onChange={props.onChange}
          />
        </div>
      </form>
    </div>
  );
}

export default SearchForm;
