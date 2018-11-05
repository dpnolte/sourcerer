declare module "LayoutTypes" {
  interface ElementBase<ViewAttributes, LayoutAttributes = {}> {
    id: string;
    attributes: ViewAttributes & LayoutAttributes;
  }
  interface NormalizedViewGroupBase<
    ViewAttributes = {},
    LayoutAttribtes ={}
  >  extends ElementBase<ViewAttributes, LayoutAttribtes>  {
    children?: Array<String>;
  }
  interface DenormalizedViewGroupBase<
    ViewAttributes = {},
    LayoutAttribtes ={},    
    ChildrenViewAttrbutes = {},
    ChildrenLayoutAttributes = {}
  >  extends ElementBase<ViewAttributes, LayoutAttribtes>  {
    children?: Array<ElementBase<ChildrenViewAttrbutes, ChildrenLayoutAttributes>>;
  }

}