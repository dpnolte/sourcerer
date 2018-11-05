/* generated @ 2018-11-05T13:39:01.670 */
import "./base.elements";
import "./generated-car.attributes";
, import "./generated.attributes";
declare module "LayoutTypes" {
  interface NormalizedColumnCardViewElement<
    LayoutAttributes={}
  > extends NormalizedViewGroupBase<ColumnCardViewAttributes, LayoutAttributes> {
    type: "columnCardView";
  }
  interface DenormalizedColumnCardViewElement<
    LayoutAttributes={},
    ChildrenViewAttributes={}
  > extends DenormalizedViewGroupBase<ColumnCardViewAttributes, LayoutAttributes, ChildrenViewAttributes, ViewGroupLayoutParamsAttributes> {
    type: "columnCardView";
  }
  interface NormalizedActionBarElement<
    LayoutAttributes={}
  > extends NormalizedViewGroupBase<ActionBarAttributes, LayoutAttributes> {
    type: "actionBar";
  }
  interface DenormalizedActionBarElement<
    LayoutAttributes={},
    ChildrenViewAttributes={}
  > extends DenormalizedViewGroupBase<ActionBarAttributes, LayoutAttributes, ChildrenViewAttributes, ViewGroupLayoutParamsAttributes> {
    type: "actionBar";
  }
  interface NormalizedPagedListViewElement<
    LayoutAttributes={}
  > extends NormalizedViewGroupBase<PagedListViewAttributes, LayoutAttributes> {
    type: "pagedListView";
  }
  interface DenormalizedPagedListViewElement<
    LayoutAttributes={},
    ChildrenViewAttributes={}
  > extends DenormalizedViewGroupBase<PagedListViewAttributes, LayoutAttributes, ChildrenViewAttributes, ViewGroupLayoutParamsAttributes> {
    type: "pagedListView";
  }
  interface NormalizedPagedScrollBarViewElement<
    LayoutAttributes={}
  > extends NormalizedViewGroupBase<PagedScrollBarViewAttributes, LayoutAttributes> {
    type: "pagedScrollBarView";
  }
  interface DenormalizedPagedScrollBarViewElement<
    LayoutAttributes={},
    ChildrenViewAttributes={}
  > extends DenormalizedViewGroupBase<PagedScrollBarViewAttributes, LayoutAttributes, ChildrenViewAttributes, ViewGroupLayoutParamsAttributes> {
    type: "pagedScrollBarView";
  }}
